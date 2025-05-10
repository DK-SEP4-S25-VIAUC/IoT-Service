package com.example.tcpserver;

import com.example.httpserver.HttpForwarder;
import com.example.httpserver.MessageBuffer;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class TCPServer {
  private final int port;
  private final HttpForwarder httpForwarder;
  private final MessageBuffer messageBuffer;

  public TCPServer(int port, HttpForwarder httpForwarder, MessageBuffer messageBuffer) {
    this.port = port;
    this.httpForwarder = httpForwarder;
    this.messageBuffer = messageBuffer;
  }

  public void start() {
    try (ServerSocket serverSocket = new ServerSocket(port)) {
      System.out.println("TCP Server listening on port " + port);

      while (true) {
        Socket socket = serverSocket.accept();
        System.out.println("New connection from " + socket.getRemoteSocketAddress());
        new Thread(() -> handleClient(socket)).start();
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  private void handleClient(Socket socket) {
    try (
        BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()))
    ) {
      String inputLine;
      while ((inputLine = reader.readLine()) != null) {
        System.out.println("Received from ESP-01: " + inputLine);

        String[] pairs = inputLine.split(",");
        Map<String, String> dataMap = new HashMap<>();

        for (String pair : pairs) {
          String[] keyValue = pair.split("=");
          if (keyValue.length == 2) {
            String key = keyValue[0].trim();
            String value = keyValue[1].trim();
            dataMap.put(key, value);
          }
        }

        // Byg JSON
        String jsonPayload = buildJsonFromMap(dataMap);
        if (jsonPayload != null) {
          httpForwarder.forwardJson(jsonPayload);
        }

        // Send evt. besked til ESP-01
        String message = messageBuffer.retrieveMessage();
        if (message != null) {
          writer.write(message);
          writer.newLine();
          writer.flush();
        }
      }
    } catch (IOException e) {
      System.err.println("Client error: " + e.getMessage());
    }
  }

  private String buildJsonFromMap(Map<String, String> map) {
    if (map.isEmpty()) return null;

    StringBuilder json = new StringBuilder("{");
    Iterator<Map.Entry<String, String>> it = map.entrySet().iterator();
    while (it.hasNext()) {
      Map.Entry<String, String> entry = it.next();
      json.append("\"")
          .append(entry.getKey())
          .append("\":\"")
          .append(entry.getValue())
          .append("\"");
      if (it.hasNext()) json.append(",");
    }
    json.append("}");
    return json.toString();
  }


}


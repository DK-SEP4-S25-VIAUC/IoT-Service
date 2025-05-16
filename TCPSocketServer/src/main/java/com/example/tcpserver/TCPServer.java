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

  //
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

        // Tjek om input er gyldigt JSON
        if (isValidJson(inputLine)) {
          httpForwarder.forwardJson(inputLine);
        } else {
          System.err.println("Invalid JSON received: " + inputLine);
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

  private boolean isValidJson(String json) {
    return json.trim().startsWith("{") && json.trim().endsWith("}");
  }


}


package com.example.httpserver;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.net.httpserver.*;

import java.io.*;
import java.net.InetSocketAddress;
import java.util.concurrent.Executors;

public class HttpControlServer {
  private final int port;
  private final MessageBuffer buffer;

  public HttpControlServer(int port, MessageBuffer buffer) {
    this.port = port;
    this.buffer = buffer;
  }

  public void start() throws IOException {
    HttpServer server = HttpServer.create(new InetSocketAddress(port), 0);
    server.createContext("/sendToEsp", exchange -> {
      if ("POST".equals(exchange.getRequestMethod())) {
        try {
          // Læs og parse JSON med ObjectMapper
          ObjectMapper mapper = new ObjectMapper();
          Object jsonObject = mapper.readValue(exchange.getRequestBody(), Object.class);

          // Konverter til kompakt (én-linjers) JSON
          String compactJson = mapper.writeValueAsString(jsonObject);

          // Gem beskeden
          buffer.storeMessage(compactJson);
          System.out.println("Stored JSON message: " + compactJson);

          // Send svar
          String response = "JSON message stored for ESP-01";
          exchange.sendResponseHeaders(200, response.getBytes().length);
          exchange.getResponseBody().write(response.getBytes());
        } catch (Exception e) {
          e.printStackTrace();
          exchange.sendResponseHeaders(500, 0);
        } finally {
          exchange.getResponseBody().close();
        }
      } else {
        exchange.sendResponseHeaders(405, -1); // Method Not Allowed
      }
    });

    server.setExecutor(Executors.newFixedThreadPool(2));
    server.start();
    System.out.println("HTTP control server on port " + port);
  }
}


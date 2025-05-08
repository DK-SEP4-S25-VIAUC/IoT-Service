package com.example.httpserver;

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
        String body = new String(exchange.getRequestBody().readAllBytes());
        buffer.storeMessage(body);
        System.out.println("Stored message: " + body);
        exchange.sendResponseHeaders(200, 0);
        exchange.getResponseBody().write("OK".getBytes());
        exchange.getResponseBody().close();
      } else {
        exchange.sendResponseHeaders(405, -1);
      }
    });

    server.setExecutor(Executors.newFixedThreadPool(2));
    server.start();
    System.out.println("HTTP control server on port " + port);
  }
}


package com.example.tcpserver;

import com.example.httpserver.HttpControlServer;
import com.example.httpserver.HttpForwarder;
import com.example.httpserver.MessageBuffer;

import java.io.IOException;


public class TCPServerApplication {
  public static void main(String[] args) throws IOException {
    MessageBuffer buffer = new MessageBuffer();
    HttpForwarder forwarder = new HttpForwarder();

    new Thread(() -> {
      TCPServer tcpServer = new TCPServer(5000, forwarder, buffer);
      tcpServer.start();
    }).start();

    HttpControlServer controlServer = new HttpControlServer(8081, buffer);
    controlServer.start();
  }
}
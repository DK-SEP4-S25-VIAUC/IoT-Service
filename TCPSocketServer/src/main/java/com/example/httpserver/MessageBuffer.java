package com.example.httpserver;

public class MessageBuffer {
  private volatile String message = null;

  public synchronized void storeMessage(String msg) {
    this.message = msg;
  }

  public synchronized String retrieveMessage() {
    String temp = message;
    message = null;
    return temp;
  }
}


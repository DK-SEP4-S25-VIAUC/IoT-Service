package com.example.tcpserver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class TCPServerApplication
{
  public static void main(String[] args) {
    int port = 5000;

    try (ServerSocket serverSocket = new ServerSocket(port)) {
      System.out.println("TCP Server listening on port " + port);

      HttpClient httpClient = HttpClient.newHttpClient();

      while (true) {
        Socket socket = serverSocket.accept();
        new Thread(() -> handleClient(socket, httpClient)).start();
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  private static void handleClient(Socket socket, HttpClient httpClient) {
    try (BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {
      String inputLine;

      while ((inputLine = reader.readLine()) != null) {
        System.out.println("Received from ESP-01: " + inputLine);

        // Build JSON payload
        String jsonPayload = "{\"soilHumidity\":\"" + inputLine + "\"}";

        // Send HTTP POST to your API
        HttpRequest request = HttpRequest.newBuilder()
            .uri(URI.create("https://iot-service-api.gentlepond-0bd2f955.northeurope.azurecontainerapps.io/api/IoT/SoilHumidity"))
            .header("Content-Type", "application/json")
            .POST(HttpRequest.BodyPublishers.ofString(jsonPayload))
            .build();

        try {
          HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
          System.out.println("HTTP POST response: " + response.statusCode() + " Body: " + response.body());
        } catch (IOException | InterruptedException e) {
          System.err.println("Failed to send HTTP request: " + e.getMessage());
        }
      }
    } catch (IOException e) {
      System.err.println("Client disconnected or error: " + e.getMessage());
    } finally {
      try {
        socket.close();
      } catch (IOException ignore) {}
    }
  }
}

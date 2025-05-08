package com.example.httpserver;

import java.net.URI;
import java.net.http.*;

public class HttpForwarder {
  private final HttpClient httpClient = HttpClient.newHttpClient();

  public void forwardJson(String json) {
    HttpRequest request = HttpRequest.newBuilder()
        .uri(URI.create("https://https://iot-service-api.gentlepond-0bd2f955.northeurope.azurecontainerapps.io/api/iot/sample"))
        .header("Content-Type", "application/json")
        .POST(HttpRequest.BodyPublishers.ofString(json))
        .build();

    try {
      HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
      System.out.println("Sent to Spring: " + response.statusCode());
    } catch (Exception e) {
      System.err.println("HTTP send failed: " + e.getMessage());
    }
  }

}

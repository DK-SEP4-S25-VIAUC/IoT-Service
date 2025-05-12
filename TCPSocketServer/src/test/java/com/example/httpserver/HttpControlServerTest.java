package com.example.httpserver;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import static org.junit.jupiter.api.Assertions.assertEquals;

class HttpControlServerTest {

  private MessageBuffer messageBuffer;
  private HttpControlServer httpControlServer;

  @BeforeEach
  void setup() {
    messageBuffer = new MessageBuffer();
    httpControlServer = new HttpControlServer(8085, messageBuffer); // Bruger port 8085 for test
    new Thread(() -> {
      try
      {
        httpControlServer.start();
      }
      catch (IOException e)
      {
        throw new RuntimeException(e);
      }
    }).start();

    // Giv serveren tid til at starte op
    try {
      Thread.sleep(1000);
    } catch (InterruptedException ignored) {}
  }

  @Test
  void testSendToEspEndpointStoresMessage() throws Exception {
    URL url = new URL("http://localhost:8085/sendtoesp");
    HttpURLConnection connection = (HttpURLConnection) url.openConnection();

    connection.setRequestMethod("POST");
    connection.setDoOutput(true);
    connection.setRequestProperty("Content-Type", "application/json");

    String jsonMessage = "{\"command\":\"action\",\"value\":50}";

    try (OutputStream os = connection.getOutputStream()) {
      os.write(jsonMessage.getBytes());
      os.flush();
    }

    int responseCode = connection.getResponseCode();
    assertEquals(200, responseCode);

    // Bekræft at beskeden er gemt som kompakt JSON
    assertEquals(jsonMessage, messageBuffer.retrieveMessage());
  }

}

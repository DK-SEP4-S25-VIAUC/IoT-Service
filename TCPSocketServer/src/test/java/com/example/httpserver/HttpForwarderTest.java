package com.example.httpserver;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.http.HttpClient;
import java.net.http.HttpResponse;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class HttpForwarderTest {

  private HttpForwarder httpForwarder;

  @BeforeEach
  void setup() {
    httpForwarder = new HttpForwarder();
  }

  @Test
  void testForwardJsonWithValidJson() {
    String json = "{\"soil_humidity\": 45.0}";

    // Du kan mocke endpointet i en rigtig test med WireMock eller lignende.
    // Her tester vi blot at metoden ikke smider exception.
    assertDoesNotThrow(() -> httpForwarder.forwardJson(json));
  }
}

package com.example.tcpserver;

import com.example.httpserver.HttpForwarder;
import com.example.httpserver.MessageBuffer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.net.Socket;

import static org.mockito.Mockito.*;

class TCPServerTest {

  private TCPServer tcpServer;
  private HttpForwarder httpForwarder;
  private MessageBuffer messageBuffer;

  @BeforeEach
  void setup() {
    httpForwarder = mock(HttpForwarder.class);
    messageBuffer = mock(MessageBuffer.class);
    tcpServer = new TCPServer(5000, httpForwarder, messageBuffer);
  }

  @Test
  void testValidJsonIsForwarded() throws Exception {
    String validJson = "{\"soil_humidity\": 45.0}";
    Socket socket = mockSocketWithInput(validJson);

    var method = TCPServer.class.getDeclaredMethod("handleClient", Socket.class);
    method.setAccessible(true);
    method.invoke(tcpServer, socket);

    verify(httpForwarder).forwardJson(validJson);
  }

  @Test
  void testInvalidJsonIsNotForwarded() throws Exception {
    String invalidInput = "soil_humidity=45.0";
    Socket socket = mockSocketWithInput(invalidInput);

    var method = TCPServer.class.getDeclaredMethod("handleClient", Socket.class);
    method.setAccessible(true);
    method.invoke(tcpServer, socket);

    verify(httpForwarder, never()).forwardJson(any());
  }

  private Socket mockSocketWithInput(String input) throws IOException {
    Socket socket = mock(Socket.class);
    InputStream is = new ByteArrayInputStream((input + System.lineSeparator()).getBytes());
    OutputStream os = new ByteArrayOutputStream();

    when(socket.getInputStream()).thenReturn(is);
    when(socket.getOutputStream()).thenReturn(os);

    return socket;
  }
}

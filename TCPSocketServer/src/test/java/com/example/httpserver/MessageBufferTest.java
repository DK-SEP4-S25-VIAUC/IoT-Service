package com.example.httpserver;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MessageBufferTest {

  @Test
  void testStoreAndRetrieveMessage() {
    MessageBuffer buffer = new MessageBuffer();
    buffer.storeMessage("hello");

    String retrieved = buffer.retrieveMessage();
    assertEquals("hello", retrieved);
  }

  @Test
  void testRetrieveClearsBuffer() {
    MessageBuffer buffer = new MessageBuffer();
    buffer.storeMessage("one");

    buffer.retrieveMessage();
    String after = buffer.retrieveMessage();
    assertNull(after);
  }
}

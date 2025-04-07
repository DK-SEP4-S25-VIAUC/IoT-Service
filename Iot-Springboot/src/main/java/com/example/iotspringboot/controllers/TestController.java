package com.example.iotspringboot.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/testendpoint")
public class TestController
{
  @GetMapping
  public String testEndpoint()
  {
    return "You have connected to the test endpoint for IoT springBoot.";
  }
}

package com.example.iotspringboot.controllers;

import com.example.iotspringboot.model.Light;
import com.example.iotspringboot.service.LightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController @RequestMapping("/api/IoT/Light") public class LightController
{
  private final LightService lightService;

  @Autowired
  public LightController(LightService lightService) {
    this.lightService = lightService;
  }

  @GetMapping("/latest")
  public Light getLatestLightReading() {
    return lightService.getLatestLightReading();
  }

  @GetMapping
  public List<Light> getAllLightReadings() {
    return lightService.getAllLights();
  }
}

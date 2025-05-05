package com.example.iotspringboot.controllers;

import com.example.iotspringboot.model.Temperature;
import com.example.iotspringboot.service.TemperatureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController @RequestMapping("/api/iot/temperature")
public class TemperatureController
{
  private final TemperatureService temperatureService;

  @Autowired
  public TemperatureController(TemperatureService temperatureService) {
    this.temperatureService = temperatureService;
  }

  @GetMapping("/latest")
  public Temperature getLatestTemperature() {
    return temperatureService.getLatestTemperature();
  }

  @GetMapping
  public List<Temperature> getAllTemperatureReadings() {
    return temperatureService.getAllTemperatures();
  }
}

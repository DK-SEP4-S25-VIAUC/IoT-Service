package com.example.iotspringboot.controllers;

import com.example.iotspringboot.dto.TemperatureDTO;
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
  public TemperatureDTO getLatestReading() {
    return temperatureService.getLatestTemperature();
  }

  @GetMapping
  public List<TemperatureDTO> getAllTemperatureReadings() {
    return temperatureService.getAllTemperatures();
  }
}

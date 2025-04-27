package com.example.iotspringboot.controllers;

import com.example.iotspringboot.model.SoilHumidity;
import com.example.iotspringboot.service.SoilHumidityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController @RequestMapping("/api/IoT/SoilHumidity")
public class SoilHumidityController
{
  private final SoilHumidityService soilHumidityService;

  @Autowired
  public SoilHumidityController(SoilHumidityService soilHumidityService) {
    this.soilHumidityService = soilHumidityService;
  }

  @GetMapping("/latest")
  public SoilHumidity getLatestSoilHumidity() {
    return soilHumidityService.getLatestSoilHumidity();
  }

  @GetMapping
  public List<SoilHumidity> getAllSoilHumidityReadings() {
    return soilHumidityService.getAllSoilHumidities();
  }
}

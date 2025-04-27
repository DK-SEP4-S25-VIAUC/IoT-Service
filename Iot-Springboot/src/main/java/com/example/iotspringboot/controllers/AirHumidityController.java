package com.example.iotspringboot.controllers;

import com.example.iotspringboot.model.AirHumidity;
import com.example.iotspringboot.service.AirHumidityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController @RequestMapping("/api/IoT/AirHumidity")
public class AirHumidityController
{
  private final AirHumidityService airHumidityService;

  @Autowired
  public AirHumidityController(AirHumidityService airHumidityService) {
    this.airHumidityService = airHumidityService;
  }

  @GetMapping("/latest")
  public AirHumidity getLatestAirHumidity() {
    return airHumidityService.getLatestAirHumidity();
  }

  @GetMapping
  public List<AirHumidity> getAllAirHumidityReadings() {
    return airHumidityService.getAllAirHumidities();
  }
}

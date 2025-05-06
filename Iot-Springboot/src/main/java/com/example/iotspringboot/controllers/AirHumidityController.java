package com.example.iotspringboot.controllers;

import com.example.iotspringboot.dto.AirHumidityDTO;
import com.example.iotspringboot.model.AirHumidity;
import com.example.iotspringboot.service.AirHumidityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController @RequestMapping("/api/iot/airhumidity")
public class AirHumidityController
{
  private final AirHumidityService airHumidityService;

  @Autowired
  public AirHumidityController(AirHumidityService airHumidityService) {
    this.airHumidityService = airHumidityService;
  }

  @GetMapping("/latest")
  public AirHumidityDTO getLatestAirHumidity() {
    return airHumidityService.getLatestAirHumidity();
  }

  @GetMapping
  public List<AirHumidityDTO> getAllAirHumidityReadings() {
    return airHumidityService.getAllAirHumidities();
  }
}

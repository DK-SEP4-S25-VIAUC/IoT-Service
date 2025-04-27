package com.example.iotspringboot.controllers;

import com.example.iotspringboot.model.Water;
import com.example.iotspringboot.service.WaterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController @RequestMapping("/api/IoT/Water")
public class WaterController
{
  private final WaterService waterService;

  @Autowired
  public WaterController(WaterService waterService) {
    this.waterService = waterService;
  }

  @GetMapping("/latest")
  public Water getLatestWaterReading() {
    return waterService.getLatestWaterReading();
  }

  @GetMapping
  public List<Water> getAllWaterReadings() {
    return waterService.getAllWaters();
  }
}

package com.example.iotspringboot.controllers;

import com.example.iotspringboot.dto.WateringDTO;
import com.example.iotspringboot.service.WateringService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController @RequestMapping("/watering")
public class WateringController
{
  @Autowired
  private WateringService wateringService;

  @PostMapping
  public WateringDTO addWatering(@RequestBody WateringDTO wateringDTO) {
    return wateringService.createWatering(wateringDTO);
  }
}

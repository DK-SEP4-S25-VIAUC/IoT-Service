package com.example.iotspringboot.controllers;

import com.example.iotspringboot.dto.WaterDTO;
import com.example.iotspringboot.model.Water;
import com.example.iotspringboot.service.WaterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController @RequestMapping("/water")
public class WaterController
{
  private final WaterService waterService;

  @Autowired
  public WaterController(WaterService waterService) {
    this.waterService = waterService;
  }

  @GetMapping("/latest")
  public WaterDTO getLatestWaterReading() {
    return waterService.getLatestWaterReading();
  }

  @GetMapping
  public List<WaterDTO> getAllWaterReadings() {
    return waterService.getAllWaters();
  }

  @PostMapping("/manual") public WaterDTO addManualWater(@RequestBody WaterDTO waterDTO)
  {
    return waterService.addWater(waterDTO);
  }

}

package com.example.iotspringboot.controllers;

import com.example.iotspringboot.dto.CreateSoilHumidityDTO;
import com.example.iotspringboot.dto.SoilHumidityDTO;
import com.example.iotspringboot.mapper.SoilHumidityMapper;
import com.example.iotspringboot.model.SoilHumidity;
import com.example.iotspringboot.service.SoilHumidityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController @RequestMapping("/api/iot/soilhumidity")
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

  @PostMapping
  public SoilHumidityDTO saveSoilHumidity(@RequestBody CreateSoilHumidityDTO request)
  {
    System.out.println("Received request: " + request.getSoil_humidity_value());
    SoilHumidity saved = soilHumidityService.saveSoilHumidity(request);
    return SoilHumidityMapper.toDTO(saved);
  }

  // TODO: Make a post endpoint threshold
}

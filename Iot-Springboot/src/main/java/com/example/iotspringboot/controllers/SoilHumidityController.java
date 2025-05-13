package com.example.iotspringboot.controllers;

import com.example.iotspringboot.dto.CreateManualThresholdDTO;
import com.example.iotspringboot.dto.CreateSoilHumidityDTO;
import com.example.iotspringboot.dto.SoilHumidityDTO;
import com.example.iotspringboot.service.ManualThresholdService;
import com.example.iotspringboot.service.SoilHumidityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.List;

@RestController @RequestMapping("/soilhumidity")
public class SoilHumidityController
{
  private final SoilHumidityService soilHumidityService;
  private final ManualThresholdService manualThresholdService;

  @Autowired
  public SoilHumidityController(SoilHumidityService soilHumidityService,
      ManualThresholdService manualThresholdService) {
    this.soilHumidityService = soilHumidityService;
    this.manualThresholdService = manualThresholdService;
  }

  @GetMapping("/latest")
  public SoilHumidityDTO getLatestSoilHumidity() {
    return soilHumidityService.getLatestSoilHumidity();
  }

  @GetMapping
  public List<SoilHumidityDTO> getSoilHumidity(
      @RequestParam(value = "from", required = false) Instant from,
      @RequestParam(value = "to", required = false) Instant to) {

    // Hent mellem tidsstempler, hvis begge parametre er til stede
    if (from != null && to != null) {
      return soilHumidityService.getSoilHumiditiesBetweenTimestamps(from, to);
    }

    // Hent fra `from` og fremad, hvis kun `from` er angivet
    if (from != null) {
      return soilHumidityService.getSoilHumidityAfterTimestamp(from);
    }

    // Hent indtil `to`, hvis kun `to` er angivet
    if (to != null) {
      return soilHumidityService.getSoilHumidityBeforeTimestamp(to);
    }

    // Returnér alle målinger, hvis ingen parametre er angivet
    return soilHumidityService.getAllSoilHumidities();
  }



  @PostMapping
  public SoilHumidityDTO saveSoilHumidity(@RequestBody CreateSoilHumidityDTO request)
  {
    System.out.println("Received request: " + request.getSoil_humidity_value());
    return soilHumidityService.saveSoilHumidity(request);
  }

  // TODO: Make a post endpoint threshold

  @PostMapping("/threshold")
  public CreateManualThresholdDTO setSoilHumidityThreshold(@RequestBody CreateManualThresholdDTO dto) {
    return manualThresholdService.setThreshold(dto);
  }

  @GetMapping("/threshold")
  public CreateManualThresholdDTO getSoilHumidityThreshold()
  {
    return manualThresholdService.getThreshold();
  }
}

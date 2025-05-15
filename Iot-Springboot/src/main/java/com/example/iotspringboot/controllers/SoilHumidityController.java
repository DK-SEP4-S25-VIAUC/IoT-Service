package com.example.iotspringboot.controllers;

import com.example.iotspringboot.dto.*;
import com.example.iotspringboot.service.ManualThresholdService;
import com.example.iotspringboot.service.SoilHumidityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.List;
import java.util.Map;

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
  public Map<String, Object> getLatestSoilHumidity() {
    SoilHumidityDTO value = soilHumidityService.getLatestSoilHumidity();
    return Map.of("SoilHumidityDTO", value);
  }

  @GetMapping
  public Map<String, Object> getSoilHumidity(
      @RequestParam(value = "from", required = false) Instant from,
      @RequestParam(value = "to", required = false) Instant to) {

    List<SoilHumidityDTO> values;

    // Hent mellem tidsstempler, hvis begge parametre er til stede
    if (from != null && to != null) {
      values = soilHumidityService.getSoilHumiditiesBetweenTimestamps(from, to);
    }

    // Hent fra `from` og fremad, hvis kun `from` er angivet
    else if (from != null) {
      values = soilHumidityService.getSoilHumidityAfterTimestamp(from);
    }

    // Hent indtil `to`, hvis kun `to` er angivet
    else if (to != null) {
      values = soilHumidityService.getSoilHumidityBeforeTimestamp(to);
    }
    // hent alle målinger, hvis ingen parametre er angivet
    else
    {
      values = soilHumidityService.getAllSoilHumidities();
    }

    List<Map<String, SoilHumidityDTO>> wrappedValues = values.stream()
        .map(dto -> Map.of("SoilHumidityDTO", dto))
        .toList();

    return Map.of("list", wrappedValues);

  }



  @PostMapping
  public SoilHumidityDTO saveSoilHumidity(@RequestBody CreateSoilHumidityDTO request)
  {
    System.out.println("Received request: " + request.getSoil_humidity_value());
    return soilHumidityService.saveSoilHumidity(request);
  }

  // TODO: Make a post endpoint threshold

  @PostMapping("/threshold")
  public Map<String, Object> setSoilHumidityThreshold(@RequestBody CreateManualThresholdDTORequest wrapper) {
     CreateManualThresholdDTO dto = wrapper.getDto();
    return Map.of("CreateManualThresholdDTO", manualThresholdService.setThreshold(dto));
  }

  @GetMapping("/threshold")
  public Map<String, Object> getSoilHumidityThreshold()
  {
    return Map.of("ThresholdSoilHumidity", manualThresholdService.getThreshold());
  }
}

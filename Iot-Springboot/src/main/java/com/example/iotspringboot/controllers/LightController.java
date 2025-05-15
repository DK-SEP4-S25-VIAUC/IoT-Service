package com.example.iotspringboot.controllers;

import com.example.iotspringboot.dto.AirHumidityDTO;
import com.example.iotspringboot.dto.LightDTO;
import com.example.iotspringboot.model.Light;
import com.example.iotspringboot.service.LightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;
import java.util.List;
import java.util.Map;

@RestController @RequestMapping("/light") public class LightController
{
  private final LightService lightService;

  @Autowired
  public LightController(LightService lightService) {
    this.lightService = lightService;
  }

  @GetMapping("/latest")
  public Map<String,Object> getLatestLightReading() {
    LightDTO value = lightService.getLatestLightReading();
    return Map.of("LightDTO", value);
  }

  @GetMapping
  public Map<String, Object> getAllLightReadings(
      @RequestParam(value = "from", required = false) Instant from,
      @RequestParam(value = "to", required = false) Instant to
  ) {
    List<LightDTO> values;

    // Hent mellem tidsstempler, hvis begge parametre er til stede
    if (from != null && to != null) {
      values = lightService.getlightsBetweenTimestamps(from, to);
    }

    // Hent fra `from` og fremad, hvis kun `from` er angivet
    else if (from != null) {
      values = lightService.getlightAfterTimestamp(from);
    }

    // Hent indtil `to`, hvis kun `to` er angivet
    else if (to != null) {
      values = lightService.getlightBeforeTimestamp(to);
    }
    // hent alle målinger, hvis ingen parametre er angivet
    else
    {
      values = lightService.getAllLights();
    }
    List<Map<String, LightDTO>> wrappedValues = values.stream()
        .map(dto -> Map.of("LightDTO", dto))
        .toList();

    return Map.of("list", wrappedValues);
  }
}

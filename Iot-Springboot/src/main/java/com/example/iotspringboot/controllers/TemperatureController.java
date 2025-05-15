package com.example.iotspringboot.controllers;

import com.example.iotspringboot.dto.AirHumidityDTO;
import com.example.iotspringboot.dto.TemperatureDTO;
import com.example.iotspringboot.service.TemperatureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;
import java.util.List;
import java.util.Map;

@RestController @RequestMapping("/temperature")
public class TemperatureController
{
  private final TemperatureService temperatureService;

  @Autowired
  public TemperatureController(TemperatureService temperatureService) {
    this.temperatureService = temperatureService;
  }

  @GetMapping("/latest")
  public Map<String, Object> getLatestTemperature() {
    TemperatureDTO value = temperatureService.getLatestTemperature();
    return Map.of("TemperatureDTO", value);
  }

  @GetMapping
  public Map<String,Object> getAllTemperatureReadings(
      @RequestParam(value = "from", required = false) Instant from,
      @RequestParam(value = "to", required = false) Instant to
  ) {

    List<TemperatureDTO> values;

    // Hent mellem tidsstempler, hvis begge parametre er til stede
    if (from != null && to != null) {
      values = temperatureService.getTemperaturesBetweenTimestamps(from, to);
    }

    // Hent fra `from` og fremad, hvis kun `from` er angivet
    else if (from != null) {
      values = temperatureService.getTemperatureAfterTimestamp(from);
    }

    // Hent indtil `to`, hvis kun `to` er angivet
    else if (to != null) {
      values = temperatureService.getTemperatureBeforeTimestamp(to);
    }
    // hent alle målinger, hvis ingen parametre er angivet
    else
    {
      values = temperatureService.getAllTemperatures();
    }

    List<Map<String, TemperatureDTO>> wrappedValues = values.stream()
        .map(dto -> Map.of("TemperatureDTO", dto))
        .toList();
    return Map.of("list", wrappedValues);
  }

}

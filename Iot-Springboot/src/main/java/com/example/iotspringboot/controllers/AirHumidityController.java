package com.example.iotspringboot.controllers;

import com.example.iotspringboot.dto.AirHumidityDTO;
import com.example.iotspringboot.dto.SampleDTO;
import com.example.iotspringboot.dto.SoilHumidityDTO;
import com.example.iotspringboot.model.AirHumidity;
import com.example.iotspringboot.service.AirHumidityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;
import java.util.List;
import java.util.Map;

@RestController @RequestMapping("/airhumidity")
public class AirHumidityController
{
  private final AirHumidityService airHumidityService;

  @Autowired
  public AirHumidityController(AirHumidityService airHumidityService) {
    this.airHumidityService = airHumidityService;
  }

  @GetMapping("/latest")
  public Map<String, Object> getLatestAirHumidity() {
    AirHumidityDTO value = airHumidityService.getLatestAirHumidity();
    return Map.of("AirHumidityDTO", value);
  }

  @GetMapping
  public Map<String, Object> getAllAirHumidityReadings(
      @RequestParam(value = "from", required = false) Instant from,
      @RequestParam(value = "to", required = false) Instant to) {

      List<AirHumidityDTO> values;

      // Hent mellem tidsstempler, hvis begge parametre er til stede
      if (from != null && to != null) {
        values = airHumidityService.getAirHumiditiesBetweenTimestamps(from, to);
      }

      // Hent fra `from` og fremad, hvis kun `from` er angivet
      else if (from != null) {
        values = airHumidityService.getAirHumidityAfterTimestamp(from);
      }

      // Hent indtil `to`, hvis kun `to` er angivet
      else if (to != null) {
        values = airHumidityService.getAirHumidityBeforeTimestamp(to);
      }
      // hent alle målinger, hvis ingen parametre er angivet
      else
      {
        values = airHumidityService.getAllAirHumidities();
      }
    List<Map<String, AirHumidityDTO>> wrappedValues = values.stream()
        .map(dto -> Map.of("AirHumidityDTO", dto))
        .toList();

    return Map.of("list", wrappedValues);
  }
}

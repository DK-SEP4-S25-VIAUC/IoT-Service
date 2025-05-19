package com.example.iotspringboot.controllers;

import com.example.iotspringboot.dto.TemperatureDTO;
import com.example.iotspringboot.service.TemperatureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.List;
import java.util.Map;

// Angiver at denne klasse er en REST API-controller
@RestController

// Alle endpoints i denne controller starter med /temperature
@RequestMapping("/temperature")
public class TemperatureController {

  private final TemperatureService temperatureService;

  // Dependency injection af temperatureService via constructor
  @Autowired
  public TemperatureController(TemperatureService temperatureService) {
    this.temperatureService = temperatureService;
  }

  /**
   * Endpoint: GET /temperature/latest
   * Beskrivelse: Henter den seneste temperaturmåling.
   * Return: En map med key "TemperatureDTO" og den seneste måling som værdi.
   */
  @GetMapping("/latest")
  public Map<String, Object> getLatestTemperature() {
    // Hent seneste måling fra service-laget
    TemperatureDTO value = temperatureService.getLatestTemperature();
    // Returnér som en map med key "TemperatureDTO"
    return Map.of("TemperatureDTO", value);
  }

  /**
   * Endpoint: GET /temperature
   * Beskrivelse: Returnerer temperaturmålinger, evt. filtreret efter tid.
   * Query-parametre:
   * - from: start-tidspunkt (valgfri)
   * - to: slut-tidspunkt (valgfri)
   * Return: Liste af målinger pakket ind i "TemperatureDTO" maps.
   */
  @GetMapping
  public Map<String,Object> getAllTemperatureReadings(
      @RequestParam(value = "from", required = false) Instant from,
      @RequestParam(value = "to", required = false) Instant to
  ) {
    List<TemperatureDTO> values;

    // Hvis både from og to er angivet: hent mellem de to tidspunkter
    if (from != null && to != null) {
      values = temperatureService.getTemperaturesBetweenTimestamps(from, to);
    }

    // Hvis kun from er angivet: hent fra tidspunktet og frem
    else if (from != null) {
      values = temperatureService.getTemperatureAfterTimestamp(from);
    }

    // Hvis kun to er angivet: hent indtil det tidspunkt
    else if (to != null) {
      values = temperatureService.getTemperatureBeforeTimestamp(to);
    }

    // Hvis ingen tidspunkter er angivet: hent alle temperaturmålinger
    else {
      values = temperatureService.getAllTemperatures();
    }

    // Wrap hver DTO i et map med key "TemperatureDTO" for struktur
    List<Map<String, TemperatureDTO>> wrappedValues = values.stream()
        .map(dto -> Map.of("TemperatureDTO", dto))
        .toList();

    // Returnér hele listen i et map med key "list"
    return Map.of("list", wrappedValues);
  }
}

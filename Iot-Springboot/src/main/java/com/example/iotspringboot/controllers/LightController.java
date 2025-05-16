package com.example.iotspringboot.controllers;

import com.example.iotspringboot.dto.LightDTO;
import com.example.iotspringboot.service.LightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.List;
import java.util.Map;

// Angiver, at denne klasse er en REST-controller
@RestController

// Alle endpoints i denne controller starter med /light
@RequestMapping("/light")
public class LightController {

  private final LightService lightService;

  // Dependency injection af LightService via constructor
  @Autowired
  public LightController(LightService lightService) {
    this.lightService = lightService;
  }

  /**
   * Endpoint: GET /light/latest
   * Beskrivelse: Henter den seneste lysmåling fra databasen.
   * Return: Et JSON-objekt med key "LightDTO" og seneste måling som værdi.
   */
  @GetMapping("/latest")
  public Map<String, Object> getLatestLightReading() {
    LightDTO value = lightService.getLatestLightReading();
    return Map.of("LightDTO", value);
  }

  /**
   * Endpoint: GET /light
   * Beskrivelse: Henter en liste af lysmålinger.
   * Query-parametre (valgfri):
   *   - from: starttidspunkt (ISO 8601, fx 2025-05-15T12:00:00Z)
   *   - to: sluttidspunkt
   * Funktion:
   *   - Hvis ingen parametre er angivet: hent alle målinger.
   *   - Hvis fra og/eller til er angivet: filtrér data.
   */
  @GetMapping
  public Map<String, Object> getAllLightReadings(
      @RequestParam(value = "from", required = false) Instant from,
      @RequestParam(value = "to", required = false) Instant to
  ) {
    List<LightDTO> values;

    // Hent målinger mellem from og to (hvis begge er angivet)
    if (from != null && to != null) {
      values = lightService.getlightsBetweenTimestamps(from, to);
    }
    // Hent målinger fra tidspunkt og frem
    else if (from != null) {
      values = lightService.getlightAfterTimestamp(from);
    }
    // Hent målinger før tidspunkt
    else if (to != null) {
      values = lightService.getlightBeforeTimestamp(to);
    }
    // Hent alle målinger
    else {
      values = lightService.getAllLights();
    }

    // Pak hver måling ind i en map med key "LightDTO" for ensartet JSON-struktur
    List<Map<String, LightDTO>> wrappedValues = values.stream()
        .map(dto -> Map.of("LightDTO", dto))
        .toList();

    // Returnér listen som JSON under key "list"
    return Map.of("list", wrappedValues);
  }
}

package com.example.iotspringboot.controllers;

import com.example.iotspringboot.dto.AirHumidityDTO;
import com.example.iotspringboot.service.AirHumidityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.List;
import java.util.Map;

// Gør klassen til en REST-controller, så den håndterer HTTP-requests
@RestController

// Base-path for alle endpoints: /airhumidity
@RequestMapping("/airhumidity")
public class AirHumidityController {

  private final AirHumidityService airHumidityService;

  // Dependency injection af AirHumidityService gennem constructor
  @Autowired
  public AirHumidityController(AirHumidityService airHumidityService) {
    this.airHumidityService = airHumidityService;
  }

  /**
   * Endpoint: GET /airhumidity/latest
   * Beskrivelse: Returnerer den seneste luftfugtighedsmåling.
   * Output: JSON med key "AirHumidityDTO" og en DTO som værdi.
   */
  @GetMapping("/latest")
  public Map<String, Object> getLatestAirHumidity() {
    AirHumidityDTO value = airHumidityService.getLatestAirHumidity();
    return Map.of("AirHumidityDTO", value);
  }

  /**
   * Endpoint: GET /airhumidity
   * Beskrivelse: Returnerer en liste af luftfugtighedsmålinger.
   * Query-parametre (valgfri):
   *   - from: starttidspunkt (ISO-8601 format)
   *   - to: sluttidspunkt
   * Funktion:
   *   - Hvis ingen parametre: returnér alle.
   *   - Hvis from og/eller to: filtrér målinger efter tid.
   * Output: JSON med key "list" og en liste af maps (én per DTO).
   */
  @GetMapping
  public Map<String, Object> getAllAirHumidityReadings(
      @RequestParam(value = "from", required = false) Instant from,
      @RequestParam(value = "to", required = false) Instant to) {

    List<AirHumidityDTO> values;

    // Hent målinger mellem to tidspunkter
    if (from != null && to != null) {
      values = airHumidityService.getAirHumiditiesBetweenTimestamps(from, to);
    }
    // Hent målinger fra et tidspunkt og frem
    else if (from != null) {
      values = airHumidityService.getAirHumidityAfterTimestamp(from);
    }
    // Hent målinger indtil et tidspunkt
    else if (to != null) {
      values = airHumidityService.getAirHumidityBeforeTimestamp(to);
    }
    // Hvis ingen filtre: hent alle målinger
    else {
      values = airHumidityService.getAllAirHumidities();
    }

    // Wrap hver måling i et map med key "AirHumidityDTO"
    List<Map<String, AirHumidityDTO>> wrappedValues = values.stream()
        .map(dto -> Map.of("AirHumidityDTO", dto))
        .toList();

    // Returnér hele listen pakket ind i en map med key "list"
    return Map.of("list", wrappedValues);
  }
}

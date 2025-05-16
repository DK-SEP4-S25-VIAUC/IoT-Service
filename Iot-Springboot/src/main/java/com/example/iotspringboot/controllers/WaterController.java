package com.example.iotspringboot.controllers;

import com.example.iotspringboot.dto.AirHumidityDTO;
import com.example.iotspringboot.dto.WaterDTO;
import com.example.iotspringboot.service.WaterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.time.Instant;
import java.util.List;
import java.util.Map;

// Angiver, at denne klasse er en REST-controller
@RestController

// Alle endpoints starter med /water
@RequestMapping("/water")
public class WaterController {

  private final WaterService waterService;

  // Dependency injection af WaterService via constructor
  @Autowired
  public WaterController(WaterService waterService) {
    this.waterService = waterService;
  }

  /**
   * Endpoint: GET /water/latest
   * Beskrivelse: Returnerer den seneste vandingmåling.
   */
  @GetMapping("/latest")
  public Map<String, Object> getLatestWaterReading() {
    WaterDTO value = waterService.getLatestWaterReading();
    // Returnér som et key-value map
    return Map.of("WaterDTO", value);
  }

  /**
   * Endpoint: GET /water
   * Beskrivelse: Returnerer alle vandingsmålinger, evt. filtreret på tidsrum.
   * Query-parametre: ?from=[ISO-tid] & to=[ISO-tid] (begge er valgfri)
   */
  @GetMapping
  public Map<String, Object> getAllWaterReadings(
      @RequestParam(value = "from", required = false) Instant from,
      @RequestParam(value = "to", required = false) Instant to) {

    List<WaterDTO> values;

    // Hvis både from og to er angivet: hent målinger mellem de to
    if (from != null && to != null) {
      values = waterService.getWaterReadingsBetweenTimestamps(from, to);
    }
    // Hvis kun from er angivet: hent fra tidspunktet og frem
    else if (from != null) {
      values = waterService.getWaterReadingsAfterTimestamp(from);
    }
    // Hvis kun to er angivet: hent målinger før tidspunktet
    else if (to != null) {
      values = waterService.getWaterReadingsBeforeTimestamp(to);
    }
    // Hvis ingen parametre er angivet: hent alle målinger
    else {
      values = waterService.getAllWaters();
    }

    // Wrap hver WaterDTO i et map for ensartet struktur
    List<Map<String, WaterDTO>> wrappedValues = values.stream()
        .map(dto -> Map.of("WaterDTO", dto))
        .toList();

    // Returnér som en liste pakket i et map med key "list"
    return Map.of("list", wrappedValues);
  }

  /**
   * Endpoint: POST /water/manual
   * Beskrivelse: Tilføj en vandingsmåling manuelt
   * Input: JSON body med WaterDTO
   */
  @PostMapping("/manual")
  public ResponseEntity<?> addManualWater(@RequestBody WaterDTO waterDTO) {
    try {
      WaterDTO saved = waterService.addWater(waterDTO);  // Gem måling
      return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    } catch (IllegalArgumentException e) {
      // Hvis input er ugyldigt, send fejlbesked
      return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
    }
  }

  /**
   * Endpoint: POST /water
   * Beskrivelse: Udløser en vanding (via TCP ESP device) og gemmer målingen.
   * Input: JSON body med WaterDTO
   */
  @PostMapping
  public ResponseEntity<?> addWateringActivity(@RequestBody WaterDTO waterDTO) {
    try {
      // TCP-endpoint som skal kontaktes
      String tcpURL = "http://4.207.72.20:8081/sendToEsp";

      // Byg JSON payload til ESP-enheden
      String tcpPayload = String.format("{\"cmd\": \"water\", \"ml\": %.2f}", waterDTO.getWatered_amount());

      // Klargør HTTP POST-request til TCP endpoint
      RestTemplate restTemplate = new RestTemplate();
      HttpHeaders headers = new HttpHeaders();
      headers.set("Content-Type", "application/json");

      HttpEntity<String> request = new HttpEntity<>(tcpPayload, headers);

      // Send request til ESP via TCP server
      ResponseEntity<String> response = restTemplate.postForEntity(tcpURL, request, String.class);

      // Hvis ESP ikke svarede med 2xx, returnér fejl
      if (!response.getStatusCode().is2xxSuccessful()) {
        return ResponseEntity.badRequest().body("Error: " + response.getStatusCode());
      }

      // Gem måling, hvis ESP-signal var OK
      waterService.addWater(waterDTO);

      return ResponseEntity.status(HttpStatus.CREATED).body(waterDTO);
    } catch (Exception e) {
      // Håndtér andre fejl
      return ResponseEntity.badRequest().body("Error: " + e.getMessage());
    }
  }

}

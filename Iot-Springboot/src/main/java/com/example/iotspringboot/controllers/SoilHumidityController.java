package com.example.iotspringboot.controllers;


import com.example.iotspringboot.dto.CreateManualThresholdDTO;
import com.example.iotspringboot.dto.CreateSoilHumidityDTO;
import com.example.iotspringboot.dto.SoilHumidityDTO;
import com.example.iotspringboot.dto.*;
import com.example.iotspringboot.service.ManualThresholdService;
import com.example.iotspringboot.service.SoilHumidityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.List;
import java.util.Map;

// Angiver at dette er en REST controller
@RestController

// Alle endpoints i denne controller starter med /soilhumidity
@RequestMapping("/soilhumidity")
public class SoilHumidityController {

  private final SoilHumidityService soilHumidityService;
  private final ManualThresholdService manualThresholdService;

  // Dependency injection af services gennem constructor
  @Autowired
  public SoilHumidityController(SoilHumidityService soilHumidityService,
      ManualThresholdService manualThresholdService) {
    this.soilHumidityService = soilHumidityService;
    this.manualThresholdService = manualThresholdService;
  }

  /**
   * Endpoint: GET /soilhumidity/latest
   * Beskrivelse: Returnerer den seneste jordfugtighedsmåling.
   */
  @GetMapping("/latest")
  public Map<String, Object> getLatestSoilHumidity() {
    SoilHumidityDTO value = soilHumidityService.getLatestSoilHumidity();
    // Pakkes i map for konsistent JSON-struktur
    return Map.of("SoilHumidityDTO", value);
  }

  /**
   * Endpoint: GET /soilhumidity
   * Beskrivelse: Returnerer liste af målinger filtreret efter tid.
   * Query-parametre: ?from= & to= (valgfri)
   */
  @GetMapping
  public Map<String, Object> getSoilHumidity(
      @RequestParam(value = "from", required = false) Instant from,
      @RequestParam(value = "to", required = false) Instant to) {

    List<SoilHumidityDTO> values;

    // Hent målinger mellem to tidspunkter
    if (from != null && to != null) {
      values = soilHumidityService.getSoilHumiditiesBetweenTimestamps(from, to);
    }
    // Hent målinger fra et tidspunkt og frem
    else if (from != null) {
      values = soilHumidityService.getSoilHumidityAfterTimestamp(from);
    }
    // Hent målinger før et tidspunkt
    else if (to != null) {
      values = soilHumidityService.getSoilHumidityBeforeTimestamp(to);
    }
    // Hvis ingen filtre: hent alle målinger
    else {
      values = soilHumidityService.getAllSoilHumidities();
    }

    // Wrap hver DTO i en map med key "SoilHumidityDTO"
    List<Map<String, SoilHumidityDTO>> wrappedValues = values.stream()
        .map(dto -> Map.of("SoilHumidityDTO", dto))
        .toList();

    // Returnér den samlede liste
    return Map.of("list", wrappedValues);
  }

  /**
   * Endpoint: POST /soilhumidity
   * Beskrivelse: Modtager ny jordfugtighedsmåling og gemmer den.
   * Body: JSON med CreateSoilHumidityDTO
   */
  @PostMapping
  public SoilHumidityDTO saveSoilHumidity(@RequestBody CreateSoilHumidityDTO request) {
    // Udskriv værdi til konsol (debugging/logging)
    System.out.println("Received request: " + request.getSoil_humidity_value());

    // Gem målingen via service og returnér DTO
    return soilHumidityService.saveSoilHumidity(request);
  }

  /**
   * Endpoint: POST /soilhumidity/threshold
   * Beskrivelse: Sæt ny manuel tærskelværdi for jordfugtighed.
   * Body: JSON med CreateManualThresholdDTO
   */
  @PostMapping("/threshold")
  public Map<String, Object> setSoilHumidityThreshold(@RequestBody CreateManualThresholdDTORequest wrapper) {
     CreateManualThresholdDTO dto = wrapper.getDto();
    return Map.of("CreateManualThresholdDTO", manualThresholdService.setThreshold(dto));
  }

  /**
   * Endpoint: GET /soilhumidity/threshold
   * Beskrivelse: Henter nuværende manuel tærskelværdi for jordfugtighed.
   */
  @GetMapping("/threshold")
  public Map<String, Object> getSoilHumidityThreshold()
  {
    return Map.of("ThresholdSoilHumidity", manualThresholdService.getThreshold());
  }
}

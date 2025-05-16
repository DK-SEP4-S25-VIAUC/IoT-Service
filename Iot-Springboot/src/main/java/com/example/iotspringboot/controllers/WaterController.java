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

@RestController @RequestMapping("/water")
public class WaterController
{
  private final WaterService waterService;

  @Autowired
  public WaterController(WaterService waterService) {
    this.waterService = waterService;
  }

  @GetMapping("/latest")
  public Map<String, Object> getLatestWaterReading() {

    WaterDTO value = waterService.getLatestWaterReading();

    return Map.of("WaterDTO", value);
  }

  @GetMapping
  public Map<String, Object> getAllWaterReadings(
      @RequestParam(value = "from", required = false) Instant from,
      @RequestParam(value = "to", required = false) Instant to) {

    List<WaterDTO> values;

    // Hent mellem tidsstempler, hvis begge parametre er til stede
    if (from != null && to != null) {
      values = waterService.getWaterReadingsBetweenTimestamps(from, to);
    }

    // Hent fra `from` og fremad, hvis kun `from` er angivet
    else if (from != null) {
      values = waterService.getWaterReadingsAfterTimestamp(from);
    }

    // Hent indtil `to`, hvis kun `to` er angivet
    else if (to != null) {
      values = waterService.getWaterReadingsBeforeTimestamp(to);
    }
    // hent alle målinger, hvis ingen parametre er angivet
    else
    {
      values = waterService.getAllWaters();
    }
    List<Map<String, WaterDTO>> wrappedValues = values.stream()
        .map(dto -> Map.of("WaterDTO", dto))
        .toList();
    return Map.of("list", wrappedValues);
  }

  @PostMapping("/manual") public ResponseEntity<?> addManualWater(@RequestBody WaterDTO waterDTO)
  {
    try {
      if (waterDTO.getWatered_amount() == null || waterDTO.getWater_level() != null) {
        throw new IllegalArgumentException("Only 'watered_amount' is allowed for this endpoint.");
      }

      WaterDTO saved = waterService.addWaterAmount(waterDTO);
      return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    } catch (IllegalArgumentException e) {
      return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
    }
  }

  @PostMapping("/waterlevel")
  public ResponseEntity<?> addWaterLevel(@RequestBody WaterDTO waterDTO) {
    try {
      if (waterDTO.getWater_level() == null || waterDTO.getWatered_amount() != null) {
        throw new IllegalArgumentException("Only 'water_level' is allowed for this endpoint.");
      }

      WaterDTO saved = waterService.addWaterLevel(waterDTO);
      return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    } catch (IllegalArgumentException e) {
      return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
    }
  }

  @PostMapping
  public ResponseEntity<?> addWateringActivity(@RequestBody WaterDTO waterDTO)
  {
    try{
      String tcpURL = "http://4.207.72.20:8081/sendToEsp";
      String tcpPayload = String.format("{\"cmd\": \"water\", \"ml\": %.2f}", waterDTO.getWatered_amount());

      RestTemplate restTemplate = new RestTemplate();
      HttpHeaders headers = new HttpHeaders();
      headers.set("Content-Type", "application/json");

      HttpEntity<String> request = new HttpEntity<>(tcpPayload, headers);

      ResponseEntity<String> response = restTemplate.postForEntity(tcpURL, request, String.class);

      if(!response.getStatusCode().is2xxSuccessful()){
        return ResponseEntity.badRequest().body("Error: " + response.getStatusCode());
      }

      waterService.addWaterAmount(waterDTO);
      return ResponseEntity.status(HttpStatus.CREATED).body(waterDTO);
    }
    catch (Exception e){
      return ResponseEntity.badRequest().body("Error: " + e.getMessage());
    }
  }

}

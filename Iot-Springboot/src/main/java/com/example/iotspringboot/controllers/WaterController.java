package com.example.iotspringboot.controllers;

import com.example.iotspringboot.dto.WaterDTO;
import com.example.iotspringboot.service.WaterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController @RequestMapping("/water")
public class WaterController
{
  private final WaterService waterService;

  @Autowired
  public WaterController(WaterService waterService) {
    this.waterService = waterService;
  }

  @GetMapping("/latest")
  public WaterDTO getLatestWaterReading() {
    return waterService.getLatestWaterReading();
  }

  @GetMapping
  public List<WaterDTO> getAllWaterReadings() {
    return waterService.getAllWaters();
  }

  @PostMapping("/manual") public WaterDTO addManualWater(@RequestBody WaterDTO waterDTO)
  {
    return waterService.addWater(waterDTO);
  }

  @PostMapping
  public ResponseEntity<?> addWateringActivity(@RequestBody WaterDTO waterDTO)
  {
    try{
      String tcpURL = "http://4.208.23.45:8081/sendToEsp";
      String tcpPayload = String.format("{\"cmd\": \"water\", \"ml\": %.2f}", waterDTO.getWatered_amount());

      RestTemplate restTemplate = new RestTemplate();
      HttpHeaders headers = new HttpHeaders();
      headers.set("Content-Type", "application/json");

      HttpEntity<String> request = new HttpEntity<>(tcpPayload, headers);

      ResponseEntity<String> response = restTemplate.postForEntity(tcpURL, request, String.class);

      if(!response.getStatusCode().is2xxSuccessful()){
        return ResponseEntity.badRequest().body("Error: " + response.getStatusCode());
      }

      waterService.addWater(waterDTO);
      return ResponseEntity.status(HttpStatus.CREATED).body(waterDTO);
    }
    catch (Exception e){
      return ResponseEntity.badRequest().body("Error: " + e.getMessage());
    }
  }

}

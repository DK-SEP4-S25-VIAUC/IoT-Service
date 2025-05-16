package com.example.iotspringboot.controllers;

import com.example.iotspringboot.dto.WateringModeDTO;
import com.example.iotspringboot.service.WateringModeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

// Marker denne klasse som en REST-controller
@RestController

// Alle endpoints i denne controller har base-path: /water/mode
@RequestMapping("/water/mode")
public class WateringModeController {

  // Service-laget, som indeholder logikken for læsning og opdatering af tilstand
  private final WateringModeService service;

  // Dependency injection via constructor
  @Autowired
  public WateringModeController(WateringModeService service) {
    this.service = service;
  }

  /**
   * Endpoint: GET /water/mode
   * Beskrivelse: Returnerer det aktuelle vandingsmode (fx auto/manual).
   * Output: JSON-repræsentation af WateringModeDTO.
   */
  @GetMapping
  public WateringModeDTO getMode() {
    return service.getMode();  // Henter aktuel tilstand fra service
  }

  /**
   * Endpoint: PUT /water/mode
   * Beskrivelse: Opdaterer vandingsmode med ny værdi (fra client).
   * Input: JSON med WateringModeDTO (fx {"mode": "manual"})
   * Output: Den opdaterede WateringModeDTO.
   */
  @PutMapping
  public WateringModeDTO updateMode(@RequestBody WateringModeDTO dto) {
    return service.setMode(dto);  // Opdaterer tilstand i service
  }
}

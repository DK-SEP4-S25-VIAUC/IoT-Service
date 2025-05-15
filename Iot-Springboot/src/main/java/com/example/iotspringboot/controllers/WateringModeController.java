package com.example.iotspringboot.controllers;

import com.example.iotspringboot.dto.WateringModeDTO;
import com.example.iotspringboot.service.WateringModeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/water/mode")
public class WateringModeController {

  private final WateringModeService service;

  @Autowired
  public WateringModeController(WateringModeService service) {
    this.service = service;
  }

  @GetMapping
  public WateringModeDTO getMode() {
    return service.getMode();
  }

  @PutMapping
  public WateringModeDTO updateMode(@RequestBody WateringModeDTO dto) {
    return service.setMode(dto);
  }
}

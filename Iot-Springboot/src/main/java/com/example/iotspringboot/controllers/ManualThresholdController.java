package com.example.iotspringboot.controllers;

import com.example.iotspringboot.dto.CreateManualThresholdDTO;
import com.example.iotspringboot.service.ManualThresholdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController @RequestMapping("/manualthreshold")
public class ManualThresholdController
{
  @Autowired
  private ManualThresholdService manualThresholdService;

  // Endpoint to set the threshold
  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public CreateManualThresholdDTO setThreshold(@RequestBody CreateManualThresholdDTO dto) {
    return manualThresholdService.setThreshold(dto);
  }

  // Endpoint to get the current threshold
  @GetMapping("/threshhold")
  @ResponseStatus(HttpStatus.OK)
  public CreateManualThresholdDTO getThreshold() {
    return manualThresholdService.getThreshold();
  }
}

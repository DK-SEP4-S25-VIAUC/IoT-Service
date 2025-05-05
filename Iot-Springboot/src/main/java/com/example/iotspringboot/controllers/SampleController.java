package com.example.iotspringboot.controllers;

import com.example.iotspringboot.model.Sample;
import com.example.iotspringboot.service.SampleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/iot/sample")
public class SampleController
{
  private final SampleService sampleService;

  @Autowired
  public SampleController(SampleService sampleService) {
    this.sampleService = sampleService;
  }

  @GetMapping
  public List<Sample> getSamples(
      @RequestParam(name = "from", required = false) Instant from,
      @RequestParam(name = "to", required = false) Instant to) {
    if (from != null && to != null) {
      return sampleService.getSamplesBetweenTimestamps(from, to);
    } else if (from != null) {
      return sampleService.getSamplesAfterTimestamp(from);
    } else if (to != null) {
      return sampleService.getSamplesBeforeTimestamp(to);
    } else {
      return sampleService.getAllSamples();
    }
  }
}

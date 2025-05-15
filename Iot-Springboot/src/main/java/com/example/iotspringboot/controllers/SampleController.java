package com.example.iotspringboot.controllers;

import com.example.iotspringboot.dto.SampleDTO;
import com.example.iotspringboot.model.Sample;
import com.example.iotspringboot.service.SampleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/sample")
public class SampleController
{
  private final SampleService sampleService;

  @Autowired
  public SampleController(SampleService sampleService) {
    this.sampleService = sampleService;
  }

  @GetMapping
  public Map<String, Object> getSamples(
      @RequestParam(name = "from", required = false) Instant from,
      @RequestParam(name = "to", required = false) Instant to) {

    List<SampleDTO> samples;

    // Hent prøverne baseret på parametrene
    if (from != null && to != null) {
      samples = sampleService.getSamplesBetweenTimestamps(from, to);
    } else if (from != null) {
      samples = sampleService.getSamplesAfterTimestamp(from);
    } else if (to != null) {
      samples = sampleService.getSamplesBeforeTimestamp(to);
    } else {
      samples = sampleService.getAllSamples();
    }

    // Bygger JSON-struktur med "SampleDTO"-wrapper
    List<Map<String, SampleDTO>> wrappedSamples = samples.stream()
        .map(sample -> Map.of("SampleDTO", sample))
        .toList();

    // Returnerer JSON i ønsket format
    return Map.of("list", wrappedSamples);
  }


  @PostMapping public SampleDTO createSample(@RequestBody SampleDTO sampleDTO)
  {
    return sampleService.saveSample(sampleDTO);
  }
}

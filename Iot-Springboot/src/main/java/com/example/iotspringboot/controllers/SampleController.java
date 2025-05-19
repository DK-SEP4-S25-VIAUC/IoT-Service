package com.example.iotspringboot.controllers;

import com.example.iotspringboot.dto.SampleDTO;
import com.example.iotspringboot.service.SampleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.List;
import java.util.Map;

// Angiver, at denne klasse er en REST-controller
@RestController

// Alle endpoints under denne controller starter med /sample
@RequestMapping("/sample")
public class SampleController {

  private final SampleService sampleService;

  // Dependency injection via constructor – Spring giver automatisk en instans af SampleService
  @Autowired
  public SampleController(SampleService sampleService) {
    this.sampleService = sampleService;
  }

  /**
   * Endpoint: GET /sample
   * Beskrivelse: Returnerer en liste af prøver (samples), evt. filtreret efter tid.
   * Query-parametre: ?from=[starttid] & to=[sluttid] (valgfri)
   * Return: JSON med en liste af SampleDTO’er pakket ind i map-struktur
   */
  @GetMapping
  public Map<String, Object> getSamples(
      @RequestParam(name = "from", required = false) Instant from,
      @RequestParam(name = "to", required = false) Instant to) {

    List<SampleDTO> samples;

    // Hvis både from og to er angivet: hent samples i tidsintervallet
    if (from != null && to != null) {
      samples = sampleService.getSamplesBetweenTimestamps(from, to);
    }
    // Hvis kun from er angivet: hent alle samples fra tidspunktet og frem
    else if (from != null) {
      samples = sampleService.getSamplesAfterTimestamp(from);
    }
    // Hvis kun to er angivet: hent alle samples før tidspunktet
    else if (to != null) {
      samples = sampleService.getSamplesBeforeTimestamp(to);
    }
    // Hvis ingen parametre er angivet: hent alle samples
    else {
      samples = sampleService.getAllSamples();
    }

    // Wrap hver sample som Map med key "SampleDTO" for struktureret JSON-output
    List<Map<String, SampleDTO>> wrappedSamples = samples.stream()
        .map(sample -> Map.of("SampleDTO", sample))
        .toList();

    // Returnér resultatet som map med key "list"
    return Map.of("list", wrappedSamples);
  }

  /**
   * Endpoint: POST /sample
   * Beskrivelse: Gemmer en ny sample, baseret på JSON input.
   * Body: JSON med SampleDTO-data
   * Return: Den gemte SampleDTO
   */
  @PostMapping
  public SampleDTO createSample(@RequestBody SampleDTO sampleDTO) {
    // Gem sample vha. service-laget og returnér resultatet
    return sampleService.saveSample(sampleDTO);
  }
}

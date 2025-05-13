package com.example.iotspringboot.service;

import com.example.iotspringboot.model.ManualThreshold;
import com.example.iotspringboot.model.WateringMode;
import com.example.iotspringboot.repository.ManualThresholdRepository;
import com.example.iotspringboot.repository.WateringModeRepository;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import com.example.iotspringboot.dto.SampleDTO;
import com.example.iotspringboot.mapper.SampleMapper;
import com.example.iotspringboot.model.Sample;
import com.example.iotspringboot.repository.SampleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.iotspringboot.model.Water;
import com.example.iotspringboot.repository.WaterRepository;


import java.time.Instant;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SampleService
{
  private final SampleRepository sampleRepository;
  private final WateringModeRepository wateringModeRepository;
  private final ManualThresholdRepository manualThresholdRepository;
  private final WaterRepository waterRepository;

  @Autowired
  public SampleService(SampleRepository sampleRepository,
      WateringModeRepository wateringModeRepository,
      ManualThresholdRepository manualThresholdRepository, WaterRepository waterRepository) {
    this.sampleRepository = sampleRepository;
    this.wateringModeRepository = wateringModeRepository;
    this.manualThresholdRepository = manualThresholdRepository;
    this.waterRepository = waterRepository;
  }


  public List<SampleDTO> getSamplesBetweenTimestamps(Instant from, Instant to) {
    return sampleRepository.findByTimeStampBetween(from, to).stream().map(
        SampleMapper::toDTO).collect(Collectors.toList());
  }

  public List<SampleDTO> getSamplesAfterTimestamp(Instant from) {
    return sampleRepository.findByTimeStampAfter(from).stream().map(
        SampleMapper::toDTO).collect(Collectors.toList());
  }

  public List<SampleDTO> getSamplesBeforeTimestamp(Instant to) {
    return sampleRepository.findByTimeStampBefore(to).stream().map(
        SampleMapper::toDTO).collect(Collectors.toList());
  }

  public List<SampleDTO> getAllSamples() {
    return sampleRepository.findAll().stream().map(
        SampleMapper::toDTO).collect(Collectors.toList());
  }

  public SampleDTO saveSample(SampleDTO sampleDTO) {
    Sample sample = SampleMapper.toEntity(sampleDTO);
    sample.setTimeStamp(Instant.now());
    Sample saved = sampleRepository.save(sample);

    checkAndTriggerWatering(saved); //automatisk vanding

    return SampleMapper.toDTO(saved);
  }


  private void checkAndTriggerWatering(Sample sample) {
    Optional<WateringMode> mode = wateringModeRepository.findById(1L);
    if (mode.isEmpty() || !mode.get().isAutomaticWatering()) return;

    Optional<ManualThreshold> thresholdOpt = manualThresholdRepository.findFirstByOrderByIdDesc();
    if (thresholdOpt.isEmpty()) return;

    ManualThreshold threshold = thresholdOpt.get();
    double soilHumidity = sample.getSoilHumidity();
    double lowerBound = threshold.getLowerbound();
    double upperBound = threshold.getUpperbound();

    // Tjek om fugtighed er under grænsen
    if (soilHumidity < lowerBound) {

      // Beregn hvor meget fugtighed vi mangler for at nå upperBound
      double difference = upperBound - soilHumidity;

      // Omregn det til ml vand – 1% kræver cirka 20 ml
      double rawAmount = difference * 20;

      // Vi vil mindst sende 100 ml og højest 1000 ml
      int mlToWater;
      if (rawAmount < 100) {
        mlToWater = 100;
      } else if (rawAmount > 1000) {
        mlToWater = 1000;
      } else {
        mlToWater = (int) rawAmount;
      }

      // Send vandingskommando
      sendWaterCommand(mlToWater);
    }
  }

  private void sendWaterCommand(int ml) {
    String url = "http://4.208.23.45:8081/sendToEsp";
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);

    Map<String, Object> body = new HashMap<>();
    body.put("cmd", "water");
    body.put("ml", ml);

    HttpEntity<Map<String, Object>> request = new HttpEntity<>(body, headers);

    try {
      new RestTemplate().postForEntity(url, request, String.class);
      System.out.println("Automatisk vanding sendt: " + ml + " ml");

      // 💾 Log automatisk vanding
      Water waterLog = new Water();
      waterLog.setWateredAmount(ml);
      waterLog.setTimeStamp(Instant.now());
      waterLog.setWaterLevel(null); // 👈 vi lader water_level være NULL

      waterRepository.save(waterLog);

    } catch (Exception e) {
      System.err.println("Fejl ved vanding: " + e.getMessage());
    }
  }




}

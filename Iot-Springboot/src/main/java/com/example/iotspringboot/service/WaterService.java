package com.example.iotspringboot.service;

import com.example.iotspringboot.dto.TemperatureDTO;
import com.example.iotspringboot.dto.WaterDTO;
import com.example.iotspringboot.mapper.TemperatureMapper;
import com.example.iotspringboot.mapper.WaterMapper;
import com.example.iotspringboot.model.Temperature;
import com.example.iotspringboot.model.Water;
import com.example.iotspringboot.repository.WaterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class WaterService
{
  private final WaterRepository waterRepository;

  @Autowired
  public WaterService(WaterRepository waterRepository) {
    this.waterRepository = waterRepository;
  }

  public WaterDTO getLatestWaterReading() {
    return WaterMapper.toDTO(waterRepository.findTopByOrderByTimeStampDesc());
  }

  public List<WaterDTO> getAllWaters() {
    return waterRepository.findAll().stream().map(WaterMapper::toDTO).collect(
        Collectors.toList());
  }

  public WaterDTO addWater(WaterDTO waterDTO)
  {
    Water entity = new Water();

    // Kun sæt værdier, hvis de er til stede
    if (waterDTO.getWatered_amount() == null) {
      entity.setWateredAmount(0.0);
    } else {
      entity.setWateredAmount(waterDTO.getWatered_amount());
    }

    if (waterDTO.getWater_level() != null) {
      entity.setWaterLevel(waterDTO.getWater_level());
    }

    entity.setTimeStamp(Instant.now());

    Water saved = waterRepository.save(entity);
    return WaterMapper.toDTO(saved);
  }

  public List<WaterDTO> getWaterReadingsBetweenTimestamps(Instant from,
      Instant to)
  {
    List<Water> temperatures = waterRepository.findByTimeStampBetween(from, to);
    return temperatures.stream().map(WaterMapper::toDTO).collect(Collectors.toList());
  }

  public List<WaterDTO> getWaterReadingsAfterTimestamp(Instant from) {
    return waterRepository.findByTimeStampAfter(from).stream()
        .map(WaterMapper::toDTO)
        .collect(Collectors.toList());
  }

  public List<WaterDTO> getWaterReadingsBeforeTimestamp(Instant to) {
    return waterRepository.findByTimeStampBefore(to).stream()
        .map(WaterMapper::toDTO)
        .collect(Collectors.toList());
  }
}

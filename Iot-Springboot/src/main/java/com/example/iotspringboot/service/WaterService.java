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

  public WaterDTO addWaterAmount(WaterDTO waterDTO) {
    Water entity = new Water();
    Double wateredAmount = waterDTO.getWatered_amount() == null ? 0.0 : waterDTO.getWatered_amount();

    if (wateredAmount < 0 || wateredAmount > 1000) {
      throw new IllegalArgumentException("Watered amount must be between 0 and 1000 ml.");
    }

    entity.setWateredAmount(wateredAmount);
    entity.setWaterLevel(null); // explicitly null
    entity.setTimeStamp(Instant.now());

    Water saved = waterRepository.save(entity);
    return WaterMapper.toDTO(saved);
  }

  public WaterDTO addWaterLevel(WaterDTO waterDTO) {
    Water entity = new Water();
    Double waterLevel = waterDTO.getWater_level();

    if (waterLevel != null && waterLevel < 0) {
      throw new IllegalArgumentException("Water level must be >= 0");
    }

    entity.setWaterLevel(waterLevel);
    entity.setWateredAmount(0.0); // explicitly 0
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

package com.example.iotspringboot.service;

import com.example.iotspringboot.dto.TemperatureDTO;
import com.example.iotspringboot.mapper.TemperatureMapper;
import com.example.iotspringboot.model.Temperature;
import com.example.iotspringboot.repository.TemperatureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TemperatureService
{
  private final TemperatureRepository temperatureRepository;

  @Autowired
  public TemperatureService(TemperatureRepository temperatureRepository) {
    this.temperatureRepository = temperatureRepository;
  }

  public TemperatureDTO getLatestTemperature() {
    return TemperatureMapper.toDTO(temperatureRepository.findTopByOrderByTimeStampDesc());
  }

  public List<TemperatureDTO> getAllTemperatures() {
    return temperatureRepository.findAll().stream().map(TemperatureMapper::toDTO).collect(
        Collectors.toList());
  }
}

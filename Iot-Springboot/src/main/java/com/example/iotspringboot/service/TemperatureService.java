package com.example.iotspringboot.service;

import com.example.iotspringboot.dto.AirHumidityDTO;
import com.example.iotspringboot.dto.TemperatureDTO;
import com.example.iotspringboot.mapper.AirHumidityMapper;
import com.example.iotspringboot.mapper.TemperatureMapper;
import com.example.iotspringboot.model.AirHumidity;
import com.example.iotspringboot.model.Temperature;
import com.example.iotspringboot.repository.TemperatureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
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

  public List<TemperatureDTO> getTemperaturesBetweenTimestamps(Instant from,
      Instant to)
  {
    List<Temperature> temperatures = temperatureRepository.findByTimeStampBetween(from, to);
    return temperatures.stream().map(TemperatureMapper::toDTO).collect(Collectors.toList());
  }

  public List<TemperatureDTO> getTemperatureAfterTimestamp(Instant from) {
    return temperatureRepository.findByTimeStampAfter(from).stream()
        .map(TemperatureMapper::toDTO)
        .collect(Collectors.toList());
  }

  public List<TemperatureDTO> getTemperatureBeforeTimestamp(Instant to) {
    return temperatureRepository.findByTimeStampBefore(to).stream()
        .map(TemperatureMapper::toDTO)
        .collect(Collectors.toList());
  }
}

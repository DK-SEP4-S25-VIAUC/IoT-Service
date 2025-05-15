package com.example.iotspringboot.service;

import com.example.iotspringboot.dto.AirHumidityDTO;
import com.example.iotspringboot.dto.LightDTO;
import com.example.iotspringboot.mapper.AirHumidityMapper;
import com.example.iotspringboot.mapper.LightMapper;
import com.example.iotspringboot.model.AirHumidity;
import com.example.iotspringboot.model.Light;
import com.example.iotspringboot.repository.LightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class LightService
{
  private final LightRepository lightRepository;

  @Autowired
  public LightService(LightRepository lightRepository) {
    this.lightRepository = lightRepository;
  }

  public LightDTO getLatestLightReading() {
    return LightMapper.toDTO(lightRepository.findTopByOrderByTimeStampDesc());
  }

  public List<LightDTO> getAllLights() {
    return lightRepository.findAll().stream().map(LightMapper::toDTO).collect(
        Collectors.toList());
  }

  public List<LightDTO> getlightsBetweenTimestamps(Instant from,
      Instant to)
  {
    List<Light> lights = lightRepository.findByTimeStampBetween(from, to);
    return lights.stream().map(LightMapper::toDTO).collect(Collectors.toList());
  }

  public List<LightDTO> getlightAfterTimestamp(Instant from) {
    return lightRepository.findByTimeStampAfter(from).stream()
        .map(LightMapper::toDTO)
        .collect(Collectors.toList());
  }

  public List<LightDTO> getlightBeforeTimestamp(Instant to) {
    return lightRepository.findByTimeStampBefore(to).stream()
        .map(LightMapper::toDTO)
        .collect(Collectors.toList());
  }

}

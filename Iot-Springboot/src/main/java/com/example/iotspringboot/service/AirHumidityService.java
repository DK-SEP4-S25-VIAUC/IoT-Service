package com.example.iotspringboot.service;

import com.example.iotspringboot.dto.AirHumidityDTO;
import com.example.iotspringboot.dto.SoilHumidityDTO;
import com.example.iotspringboot.mapper.AirHumidityMapper;
import com.example.iotspringboot.mapper.SoilHumidityMapper;
import com.example.iotspringboot.model.AirHumidity;
import com.example.iotspringboot.model.SoilHumidity;
import com.example.iotspringboot.repository.AirHumidityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AirHumidityService
{
  private final AirHumidityRepository airHumidityRepository;

  @Autowired
  public AirHumidityService(AirHumidityRepository airHumidityRepository) {
    this.airHumidityRepository = airHumidityRepository;
  }

  public AirHumidityDTO getLatestAirHumidity() {
    return AirHumidityMapper.toDTO(airHumidityRepository.findTopByOrderByTimeStampDesc());
  }

  public List<AirHumidityDTO> getAllAirHumidities() {
    return airHumidityRepository.findAll().stream().map(AirHumidityMapper::toDTO).collect(
        Collectors.toList());
  }

  public List<AirHumidityDTO> getAirHumiditiesBetweenTimestamps(Instant from,
      Instant to)
  {
    List<AirHumidity> airHumidities = airHumidityRepository.findByTimeStampBetween(from, to);
    return airHumidities.stream().map(AirHumidityMapper::toDTO).collect(Collectors.toList());
  }

  public List<AirHumidityDTO> getAirHumidityAfterTimestamp(Instant from) {
    return airHumidityRepository.findByTimeStampAfter(from).stream()
        .map(AirHumidityMapper::toDTO)
        .collect(Collectors.toList());
  }

  public List<AirHumidityDTO> getAirHumidityBeforeTimestamp(Instant to) {
    return airHumidityRepository.findByTimeStampBefore(to).stream()
        .map(AirHumidityMapper::toDTO)
        .collect(Collectors.toList());
  }
}

package com.example.iotspringboot.service;

import com.example.iotspringboot.dto.CreateSoilHumidityDTO;
import com.example.iotspringboot.dto.SoilHumidityDTO;
import com.example.iotspringboot.mapper.SoilHumidityMapper;
import com.example.iotspringboot.model.Sample;
import com.example.iotspringboot.model.SoilHumidity;
import com.example.iotspringboot.repository.SoilHumidityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SoilHumidityService
{
  private final SoilHumidityRepository soilHumidityRepository;

  @Autowired
  public SoilHumidityService(SoilHumidityRepository soilHumidityRepository) {
    this.soilHumidityRepository = soilHumidityRepository;
  }

  public SoilHumidityDTO getLatestSoilHumidity() {

    SoilHumidity entity = soilHumidityRepository.findTopByOrderByTimeStampDesc();
    if (entity == null) {
      return null;
    }
    return SoilHumidityMapper.toDTO(entity);  }

  public List<SoilHumidityDTO> getAllSoilHumidities() {
    return soilHumidityRepository.findAll().stream().map(SoilHumidityMapper::toDTO).collect(
        Collectors.toList());
  }

  public SoilHumidityDTO saveSoilHumidity(CreateSoilHumidityDTO dto) {
    SoilHumidity entity = new SoilHumidity();
    entity.setSoilHumidityValue(dto.getSoil_humidity_value());
    entity.setTimeStamp(Instant.now());

    SoilHumidity saved = soilHumidityRepository.save(entity);
    return SoilHumidityMapper.toDTO(saved);

  }

  public List<SoilHumidityDTO> getSoilHumiditiesBetweenTimestamps(Instant from,
      Instant to)
  {
    List<SoilHumidity> soilHumidities = soilHumidityRepository.findByTimeStampBetween(from, to);
    return soilHumidities.stream().map(SoilHumidityMapper::toDTO).collect(Collectors.toList());
  }

  public List<SoilHumidityDTO> getSoilHumidityAfterTimestamp(Instant from) {
    return soilHumidityRepository.findByTimeStampAfter(from).stream()
        .map(SoilHumidityMapper::toDTO)
        .collect(Collectors.toList());
  }

  public List<SoilHumidityDTO> getSoilHumidityBeforeTimestamp(Instant to) {
    return soilHumidityRepository.findByTimeStampBefore(to).stream()
        .map(SoilHumidityMapper::toDTO)
        .collect(Collectors.toList());
  }




}

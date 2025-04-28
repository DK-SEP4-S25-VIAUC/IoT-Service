package com.example.iotspringboot.service;

import com.example.iotspringboot.dto.CreateSoilHumidityDTO;
import com.example.iotspringboot.dto.SoilHumidityDTO;
import com.example.iotspringboot.model.SoilHumidity;
import com.example.iotspringboot.repository.SoilHumidityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;

@Service
public class SoilHumidityService
{
  private final SoilHumidityRepository soilHumidityRepository;

  @Autowired
  public SoilHumidityService(SoilHumidityRepository soilHumidityRepository) {
    this.soilHumidityRepository = soilHumidityRepository;
  }

  public SoilHumidity getLatestSoilHumidity() {
    return soilHumidityRepository.findTopByOrderByTimeStampDesc();
  }

  public List<SoilHumidity> getAllSoilHumidities() {
    return soilHumidityRepository.findAll();
  }

  public SoilHumidity saveSoilHumidity(CreateSoilHumidityDTO dto) {
    SoilHumidity entity = new SoilHumidity();
    entity.setSoilHumidityValue(dto.getSoil_humidity_value());
    entity.setTimeStamp(Instant.now()); // Sæt tidspunktet her på serversiden

    return soilHumidityRepository.save(entity);
  }

  public static SoilHumidityDTO toDTO(SoilHumidity soilHumidity) {
    SoilHumidityDTO dto = new SoilHumidityDTO();
    dto.setId(soilHumidity.getId());
    dto.setTime_stamp(ZonedDateTime.ofInstant(soilHumidity.getTimeStamp(), ZoneId.of("Europe/Copenhagen")));
    dto.setSoil_humidity_value(soilHumidity.getSoilHumidityValue());
    return dto;
  }


}

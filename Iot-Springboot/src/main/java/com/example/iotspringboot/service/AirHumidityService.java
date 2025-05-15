package com.example.iotspringboot.service;

import com.example.iotspringboot.dto.AirHumidityDTO;
import com.example.iotspringboot.mapper.AirHumidityMapper;
import com.example.iotspringboot.model.AirHumidity;
import com.example.iotspringboot.repository.AirHumidityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

  /*/
  public AirHumidityDTO getLatestAirHumidity() {
    return AirHumidityMapper.toDTO(airHumidityRepository.findTopByOrderByTimeStampDesc());
  }
/*/

  public List<AirHumidityDTO> getAllAirHumidities() {
    return airHumidityRepository.findAll().stream().map(AirHumidityMapper::toDTO).collect(
        Collectors.toList());
  }

  // null check, for at komme igennem testen.
  public AirHumidityDTO getLatestAirHumidity() {
    AirHumidity latest = airHumidityRepository.findTopByOrderByTimeStampDesc();
    if (latest == null) {
      return null;
    }
    return AirHumidityMapper.toDTO(latest);
  }





}

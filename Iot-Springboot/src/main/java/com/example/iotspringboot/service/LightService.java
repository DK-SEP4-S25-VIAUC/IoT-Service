package com.example.iotspringboot.service;

import com.example.iotspringboot.dto.LightDTO;
import com.example.iotspringboot.mapper.LightMapper;
import com.example.iotspringboot.model.Light;
import com.example.iotspringboot.repository.LightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

  /*/ public LightDTO getLatestLightReading() {
    return LightMapper.toDTO(lightRepository.findTopByOrderByTimeStampDesc());
  }
/*/
  public List<LightDTO> getAllLights() {
    return lightRepository.findAll().stream().map(LightMapper::toDTO).collect(
        Collectors.toList());
  }

  //krav for at få testen passed.
  public LightDTO getLatestLightReading() {
    Light latestLight = lightRepository.findTopByOrderByTimeStampDesc();
    if (latestLight == null) {
      return null;
    }
    return LightMapper.toDTO(latestLight);
  }


}

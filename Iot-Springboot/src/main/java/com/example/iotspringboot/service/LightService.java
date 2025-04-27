package com.example.iotspringboot.service;

import com.example.iotspringboot.model.Light;
import com.example.iotspringboot.repository.LightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LightService
{
  private final LightRepository lightRepository;

  @Autowired
  public LightService(LightRepository lightRepository) {
    this.lightRepository = lightRepository;
  }

  public Light getLatestLightReading() {
    return lightRepository.findTopByOrderByTimeStampDesc();
  }

  public List<Light> getAllLights() {
    return lightRepository.findAll();
  }

}

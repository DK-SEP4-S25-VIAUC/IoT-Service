package com.example.iotspringboot.service;

import com.example.iotspringboot.model.Temperature;
import com.example.iotspringboot.repository.TemperatureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TemperatureService
{
  private final TemperatureRepository temperatureRepository;

  @Autowired
  public TemperatureService(TemperatureRepository temperatureRepository) {
    this.temperatureRepository = temperatureRepository;
  }

  public Temperature getLatestTemperature() {
    return temperatureRepository.findTopByOrderByTimeStampDesc();
  }

  public List<Temperature> getAllTemperatures() {
    return temperatureRepository.findAll();
  }
}

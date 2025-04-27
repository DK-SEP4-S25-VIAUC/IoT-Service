package com.example.iotspringboot.service;

import com.example.iotspringboot.model.SoilHumidity;
import com.example.iotspringboot.repository.SoilHumidityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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


}

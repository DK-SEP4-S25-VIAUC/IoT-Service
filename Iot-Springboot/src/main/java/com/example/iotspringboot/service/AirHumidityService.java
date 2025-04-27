package com.example.iotspringboot.service;

import com.example.iotspringboot.model.AirHumidity;
import com.example.iotspringboot.repository.AirHumidityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AirHumidityService
{
  private final AirHumidityRepository airHumidityRepository;

  @Autowired
  public AirHumidityService(AirHumidityRepository airHumidityRepository) {
    this.airHumidityRepository = airHumidityRepository;
  }

  public AirHumidity getLatestAirHumidity() {
    return airHumidityRepository.findTopByOrderByTimeStampDesc();
  }

  public List<AirHumidity> getAllAirHumidities() {
    return airHumidityRepository.findAll();
  }
}

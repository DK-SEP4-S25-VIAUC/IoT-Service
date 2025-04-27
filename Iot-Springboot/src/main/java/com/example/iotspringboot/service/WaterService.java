package com.example.iotspringboot.service;

import com.example.iotspringboot.model.Water;
import com.example.iotspringboot.repository.WaterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WaterService
{
  private final WaterRepository waterRepository;

  @Autowired
  public WaterService(WaterRepository waterRepository) {
    this.waterRepository = waterRepository;
  }

  public Water getLatestWaterReading() {
    return waterRepository.findTopByOrderByTimeStampDesc();
  }

  public List<Water> getAllWaters() {
    return waterRepository.findAll();
  }
}

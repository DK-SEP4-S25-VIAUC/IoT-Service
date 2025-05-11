package com.example.iotspringboot.service;

import com.example.iotspringboot.dto.WaterDTO;
import com.example.iotspringboot.mapper.WaterMapper;
import com.example.iotspringboot.model.Water;
import com.example.iotspringboot.repository.WaterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class WaterService
{
  private final WaterRepository waterRepository;

  @Autowired
  public WaterService(WaterRepository waterRepository) {
    this.waterRepository = waterRepository;
  }

  public WaterDTO getLatestWaterReading() {
    return WaterMapper.toDTO(waterRepository.findTopByOrderByTimeStampDesc());
  }

  public List<WaterDTO> getAllWaters() {
    return waterRepository.findAll().stream().map(WaterMapper::toDTO).collect(
        Collectors.toList());
  }
}

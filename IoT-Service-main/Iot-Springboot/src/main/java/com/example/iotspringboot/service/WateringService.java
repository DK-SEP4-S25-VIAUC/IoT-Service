package com.example.iotspringboot.service;

import com.example.iotspringboot.dto.WateringDTO;
import com.example.iotspringboot.mapper.WateringMapper;
import com.example.iotspringboot.model.Watering;
import com.example.iotspringboot.repository.AirHumidityRepository;
import com.example.iotspringboot.repository.WateringRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WateringService
{

  private final WateringRepository wateringRepository;

  @Autowired public WateringService(WateringRepository wateringRepository)
  {
    this.wateringRepository = wateringRepository;
  }

  public WateringDTO addWatering(WateringDTO wateringDTO) {
    Watering watering = WateringMapper.toEntity(wateringDTO);
    Watering savedWatering = wateringRepository.save(watering);
    return WateringMapper.toDTO(savedWatering);
  }


}

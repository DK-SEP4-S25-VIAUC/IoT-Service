package com.example.iotspringboot.mapper;

import com.example.iotspringboot.dto.WateringModeDTO;
import com.example.iotspringboot.model.WateringMode;

public class WateringMapper
{
  public static WateringModeDTO toDTO(WateringMode watering) {
    WateringModeDTO dto = new WateringModeDTO();
    dto.setWater_amount(watering.getWaterAmount());
    return dto;
  }

  public static WateringMode toEntity(WateringModeDTO dto) {
    WateringMode watering = new WateringMode();
    watering.setWaterAmount(dto.getWater_amount());
    return watering;
  }
}

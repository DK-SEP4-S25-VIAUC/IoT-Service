package com.example.iotspringboot.mapper;

import com.example.iotspringboot.dto.WateringDTO;
import com.example.iotspringboot.model.Watering;

public class WateringMapper
{
  public static WateringDTO toDTO(Watering watering) {
    WateringDTO dto = new WateringDTO();
    dto.setWater_amount(watering.getWaterAmount());
    return dto;
  }

  public static Watering toEntity(WateringDTO dto) {
    Watering watering = new Watering();
    watering.setWaterAmount(dto.getWater_amount());
    return watering;
  }
}

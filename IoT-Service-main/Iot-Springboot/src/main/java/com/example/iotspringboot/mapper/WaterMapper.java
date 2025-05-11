package com.example.iotspringboot.mapper;

import com.example.iotspringboot.dto.WaterDTO;
import com.example.iotspringboot.model.Water;

public class WaterMapper
{
  public static WaterDTO toDTO(Water water) {
    WaterDTO dto = new WaterDTO();
    dto.setId(water.getId());
    dto.setTimestamp(water.getTimeStamp());
    dto.setWaterLevel(water.getWaterLevel());
    dto.setWateredAmount(water.getWateredAmount());
    return dto;
  }

  public static Water toEntity(WaterDTO dto) {
    Water water = new Water();
    water.setId(dto.getId());
    water.setTimeStamp(dto.getTimestamp());
    water.setWaterLevel(dto.getWaterLevel());
    water.setWateredAmount(dto.getWateredAmount());
    return water;
  }
}

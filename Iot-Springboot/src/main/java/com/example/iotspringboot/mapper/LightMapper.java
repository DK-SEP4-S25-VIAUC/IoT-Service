package com.example.iotspringboot.mapper;

import com.example.iotspringboot.dto.LightDTO;
import com.example.iotspringboot.model.Light;

public class LightMapper
{
  public static LightDTO toDTO(Light light) {
    LightDTO dto = new LightDTO();
    dto.setId(light.getId());
    dto.setTime_stamp(light.getTimeStamp());
    dto.setLight_value(light.getLightValue());
    return dto;
  }

  public static Light toEntity(LightDTO dto) {
    Light light = new Light();
    light.setId(dto.getId());
    light.setTimeStamp(dto.getTime_stamp());
    light.setLightValue(dto.getLight_value());
    return light;
  }
}

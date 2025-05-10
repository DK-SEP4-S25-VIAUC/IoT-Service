package com.example.iotspringboot.mapper;

import com.example.iotspringboot.dto.AirHumidityDTO;
import com.example.iotspringboot.model.AirHumidity;

public class AirHumidityMapper
{
  public static AirHumidityDTO toDTO(AirHumidity airHumidity) {
    AirHumidityDTO dto = new AirHumidityDTO();
    dto.setId(airHumidity.getId());
    dto.setTime_stamp(airHumidity.getTimeStamp());
    dto.setAirHumidityValue(airHumidity.getAirHumidityValue());
    return dto;
  }

  public static AirHumidity toEntity(AirHumidityDTO dto) {
    AirHumidity airHumidity = new AirHumidity();
    airHumidity.setId(dto.getId());
    airHumidity.setTimeStamp(dto.getTime_stamp());
    airHumidity.setAirHumidityValue(dto.getAirHumidityValue());
    return airHumidity;
  }
}

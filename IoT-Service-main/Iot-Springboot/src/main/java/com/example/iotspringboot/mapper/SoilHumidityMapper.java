package com.example.iotspringboot.mapper;

import com.example.iotspringboot.dto.SoilHumidityDTO;
import com.example.iotspringboot.model.SoilHumidity;
import java.time.ZoneId;
import java.time.ZonedDateTime;

public class SoilHumidityMapper
{
  public static SoilHumidityDTO toDTO(SoilHumidity soilHumidity) {
    SoilHumidityDTO dto = new SoilHumidityDTO();
    dto.setId(soilHumidity.getId());
    dto.setTime_stamp(ZonedDateTime.ofInstant(soilHumidity.getTimeStamp(), ZoneId.of("Europe/Copenhagen")));
    dto.setSoil_humidity_value(soilHumidity.getSoilHumidityValue());
    return dto;
  }

  public static SoilHumidity toEntity(SoilHumidityDTO dto) {
    SoilHumidity soilHumidity = new SoilHumidity();
    soilHumidity.setId(dto.getId());
    dto.setTime_stamp(soilHumidity.getTimeStamp().atZone(ZoneId.of("Europe/Copenhagen")));
    soilHumidity.setSoilHumidityValue(dto.getSoil_humidity_value());
    return soilHumidity;
  }
}

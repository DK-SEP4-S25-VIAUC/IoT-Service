package com.example.iotspringboot.mapper;

import com.example.iotspringboot.dto.TemperatureDTO;
import com.example.iotspringboot.model.Temperature;

public class TemperatureMapper
{
  public static TemperatureDTO toDTO(Temperature temperature) {
    TemperatureDTO dto = new TemperatureDTO();
    dto.setId(temperature.getId());
    dto.setTime_stamp(temperature.getTimeStamp());
    dto.setTemperature_value(temperature.getTemperatureValue());
    return dto;
  }

  public static Temperature toEntity(TemperatureDTO dto) {
    Temperature temperature = new Temperature();
    temperature.setId(dto.getId());
    temperature.setTimeStamp(dto.getTime_stamp());
    temperature.setTemperatureValue(dto.getTemperature_value());
    return temperature;
  }
}

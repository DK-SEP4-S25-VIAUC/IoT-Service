package com.example.iotspringboot.mapper;

import com.example.iotspringboot.dto.SampleDTO;
import com.example.iotspringboot.model.Sample;

public class SampleMapper
{
  public static SampleDTO toDTO(Sample sample) {
    SampleDTO dto = new SampleDTO();
    dto.setId(sample.getId());
    dto.setSoil_humidity(sample.getSoilHumidity());
    dto.setAir_humidity(sample.getAirHumidity());
    dto.setAir_temperature(sample.getAirTemperature());
    dto.setLight_value(sample.getLightValue());
    dto.setTime_stamp(sample.getTimeStamp());
    return dto;
  }

  public static Sample toEntity(SampleDTO dto) {
    Sample sample = new Sample();
    sample.setId(dto.getId());
    sample.setSoilHumidity(dto.getSoil_humidity());
    sample.setAirHumidity(dto.getAir_humidity());
    sample.setAirTemperature(dto.getAir_temperature());
    sample.setLightValue(dto.getLight_value());
    sample.setTimeStamp(dto.getTime_stamp());
    return sample;
  }
}

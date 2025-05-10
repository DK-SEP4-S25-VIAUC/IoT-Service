package com.example.iotspringboot.mapper;

import com.example.iotspringboot.dto.SampleDTO;
import com.example.iotspringboot.model.Sample;

public class SampleMapper
{
  public static SampleDTO toDTO(Sample sample) {
    SampleDTO dto = new SampleDTO();
    dto.setId(sample.getId());
    dto.setSoilHumidity(sample.getSoilHumidity());
    dto.setAirHumidity(sample.getAirHumidity());
    dto.setAirTemperature(sample.getAirTemperature());
    dto.setLightValue(sample.getLightValue());
    dto.setTimestamp(sample.getTimeStamp());
    return dto;
  }

  public static Sample toEntity(SampleDTO dto) {
    Sample sample = new Sample();
    sample.setId(dto.getId());
    sample.setSoilHumidity(dto.getSoilHumidity());
    sample.setAirHumidity(dto.getAirHumidity());
    sample.setAirTemperature(dto.getAirTemperature());
    sample.setLightValue(dto.getLightValue());
    sample.setTimeStamp(dto.getTimestamp());
    return sample;
  }
}

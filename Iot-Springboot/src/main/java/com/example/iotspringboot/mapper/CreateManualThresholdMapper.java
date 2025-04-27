package com.example.iotspringboot.mapper;

import com.example.iotspringboot.dto.CreateManualThresholdDTO;
import com.example.iotspringboot.model.ManualThreshold;

public class CreateManualThresholdMapper
{
  public static CreateManualThresholdDTO toDTO(ManualThreshold threshold) {
    CreateManualThresholdDTO dto = new CreateManualThresholdDTO();
    dto.setLowerbound(threshold.getLowerbound());
    dto.setUpperbound(threshold.getUpperbound());
    return dto;
  }

  public static ManualThreshold toEntity(CreateManualThresholdDTO dto) {
    ManualThreshold threshold = new ManualThreshold();
    threshold.setLowerbound(dto.getLowerbound());
    threshold.setUpperbound(dto.getUpperbound());
    return threshold;
  }
}

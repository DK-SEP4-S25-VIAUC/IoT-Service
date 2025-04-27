package com.example.iotspringboot.service;

import com.example.iotspringboot.dto.CreateManualThresholdDTO;
import com.example.iotspringboot.mapper.CreateManualThresholdMapper;
import com.example.iotspringboot.model.ManualThreshold;
import com.example.iotspringboot.repository.ManualThresholdRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ManualThresholdService
{
  @Autowired
  private ManualThresholdRepository manualThresholdRepository;

  public CreateManualThresholdDTO setThreshold(CreateManualThresholdDTO dto) {
    // Convert DTO to entity
    ManualThreshold manualThreshold = CreateManualThresholdMapper.toEntity(dto);

    // Save the entity
    ManualThreshold savedThreshold = manualThresholdRepository.save(manualThreshold);

    // Return saved DTO (Could be the same DTO or entity depending on what you need)
    return CreateManualThresholdMapper.toDTO(savedThreshold);
  }

  public CreateManualThresholdDTO getThreshold() {
    // Get the threshold (assuming there's only one record)
    ManualThreshold threshold = manualThresholdRepository.findById(1L).orElseThrow(
        () -> new RuntimeException("Threshold not set!")
    );
    return CreateManualThresholdMapper.toDTO(threshold);
  }
}

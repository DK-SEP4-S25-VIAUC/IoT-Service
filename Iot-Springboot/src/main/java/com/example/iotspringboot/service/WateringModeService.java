package com.example.iotspringboot.service;

import com.example.iotspringboot.dto.WateringModeDTO;
import com.example.iotspringboot.model.WateringMode;
import com.example.iotspringboot.repository.WateringModeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class WateringModeService {

  private final WateringModeRepository repository;

  @Autowired
  public WateringModeService(WateringModeRepository repository) {
    this.repository = repository;
  }

  public WateringModeDTO getMode() {
    Optional<WateringMode> mode = repository.findById(1L);
    WateringMode m = mode.orElseGet(() -> {
      WateringMode newMode = new WateringMode();
      newMode.setId(1L);
      newMode.setAutomaticWatering(false);
      return repository.save(newMode);
    });

    WateringModeDTO dto = new WateringModeDTO();
    dto.setAutomaticWatering(m.isAutomaticWatering());
    return dto;
  }

  public WateringModeDTO setMode(WateringModeDTO dto) {
    WateringMode m = repository.findById(1L).orElse(new WateringMode());
    m.setId(1L);
    m.setAutomaticWatering(dto.isAutomaticWatering());
    repository.save(m);
    return dto;
  }
}

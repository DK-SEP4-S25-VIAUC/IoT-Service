package com.example.iotspringboot.repository;

import com.example.iotspringboot.model.WateringMode;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WateringModeRepository extends JpaRepository<WateringMode, Long> {
}

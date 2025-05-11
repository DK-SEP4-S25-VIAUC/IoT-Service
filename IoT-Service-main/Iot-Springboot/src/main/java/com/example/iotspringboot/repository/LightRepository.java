package com.example.iotspringboot.repository;

import com.example.iotspringboot.model.Light;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LightRepository extends JpaRepository<Light, Long>
{
  Light findTopByOrderByTimeStampDesc();
}

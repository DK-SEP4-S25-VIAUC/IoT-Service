package com.example.iotspringboot.repository;

import com.example.iotspringboot.model.Light;
import com.example.iotspringboot.model.SoilHumidity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.Instant;
import java.util.List;

public interface LightRepository extends JpaRepository<Light, Long>
{
  Light findTopByOrderByTimeStampDesc();

  List<Light> findByTimeStampBetween(Instant from, Instant to);

  List<Light> findByTimeStampAfter(Instant from);

  List<Light> findByTimeStampBefore(Instant to);
}

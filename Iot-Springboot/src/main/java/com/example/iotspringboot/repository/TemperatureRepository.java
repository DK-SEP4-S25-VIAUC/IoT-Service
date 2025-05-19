package com.example.iotspringboot.repository;

import com.example.iotspringboot.model.SoilHumidity;
import com.example.iotspringboot.model.Temperature;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.List;

@Repository
public interface TemperatureRepository extends JpaRepository<Temperature, Long>
{
  Temperature findTopByOrderByTimeStampDesc();

  List<Temperature> findByTimeStampBetween(Instant from, Instant to);

  List<Temperature> findByTimeStampAfter(Instant from);

  List<Temperature> findByTimeStampBefore(Instant to);
}

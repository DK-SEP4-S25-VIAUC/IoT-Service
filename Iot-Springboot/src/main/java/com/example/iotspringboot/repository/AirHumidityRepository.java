package com.example.iotspringboot.repository;

import com.example.iotspringboot.model.AirHumidity;
import com.example.iotspringboot.model.SoilHumidity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.List;

@Repository
public interface AirHumidityRepository extends JpaRepository<AirHumidity, Long>
{
  AirHumidity findTopByOrderByTimeStampDesc();

  List<AirHumidity> findByTimeStampBetween(Instant from, Instant to);

  List<AirHumidity> findByTimeStampAfter(Instant from);

  List<AirHumidity> findByTimeStampBefore(Instant to);
}

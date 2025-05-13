package com.example.iotspringboot.repository;

import com.example.iotspringboot.model.SoilHumidity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.List;

@Repository

public interface SoilHumidityRepository extends JpaRepository<SoilHumidity, Long>
{
  SoilHumidity findTopByOrderByTimeStampDesc();

  List<SoilHumidity> findByTimeStampBetween(Instant from, Instant to);

  List<SoilHumidity> findByTimeStampAfter(Instant from);

  List<SoilHumidity> findByTimeStampBefore(Instant to);

}

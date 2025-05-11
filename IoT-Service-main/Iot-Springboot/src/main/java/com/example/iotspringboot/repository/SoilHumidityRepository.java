package com.example.iotspringboot.repository;

import com.example.iotspringboot.model.SoilHumidity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface SoilHumidityRepository extends JpaRepository<SoilHumidity, Long>
{
  SoilHumidity findTopByOrderByTimeStampDesc();
}

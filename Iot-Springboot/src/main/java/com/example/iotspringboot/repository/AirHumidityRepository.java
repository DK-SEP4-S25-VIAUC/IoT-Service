package com.example.iotspringboot.repository;

import com.example.iotspringboot.model.AirHumidity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AirHumidityRepository extends JpaRepository<AirHumidity, Long>
{
  AirHumidity findTopByOrderByTimeStampDesc();
}

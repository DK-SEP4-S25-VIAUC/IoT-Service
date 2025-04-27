package com.example.iotspringboot.repository;

import com.example.iotspringboot.model.Temperature;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TemperatureRepository extends JpaRepository<Temperature, Long>
{
  Temperature findTopByOrderByTimeStampDesc();
}

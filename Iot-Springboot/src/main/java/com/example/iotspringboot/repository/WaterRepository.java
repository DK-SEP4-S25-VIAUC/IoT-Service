package com.example.iotspringboot.repository;

import com.example.iotspringboot.model.Temperature;
import com.example.iotspringboot.model.Water;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.List;

@Repository

public interface WaterRepository extends JpaRepository<Water, Long>
{
  Water findTopByOrderByTimeStampDesc();

  List<Water> findByTimeStampBetween(Instant from, Instant to);

  List<Water> findByTimeStampAfter(Instant from);

  List<Water> findByTimeStampBefore(Instant to);
}

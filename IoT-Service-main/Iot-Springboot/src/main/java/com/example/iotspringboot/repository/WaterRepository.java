package com.example.iotspringboot.repository;

import com.example.iotspringboot.model.Water;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface WaterRepository extends JpaRepository<Water, Long>
{
  Water findTopByOrderByTimeStampDesc();
}

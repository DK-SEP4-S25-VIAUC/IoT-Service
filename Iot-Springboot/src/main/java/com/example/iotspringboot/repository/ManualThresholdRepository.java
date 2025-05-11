package com.example.iotspringboot.repository;

import com.example.iotspringboot.model.ManualThreshold;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ManualThresholdRepository extends JpaRepository<ManualThreshold, Long>
{
  Optional<ManualThreshold> findFirstByOrderByIdDesc();
}

package com.example.iotspringboot.repository;

import com.example.iotspringboot.model.Sample;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.List;
@Repository
public interface SampleRepository extends JpaRepository<Sample, Long>
{
  List<Sample> findByTimeStampBetween(Instant from, Instant to);

  List<Sample> findByTimeStampAfter(Instant from);

  List<Sample> findByTimeStampBefore(Instant to);
}

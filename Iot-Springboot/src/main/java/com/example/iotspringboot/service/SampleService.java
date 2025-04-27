package com.example.iotspringboot.service;

import com.example.iotspringboot.model.Sample;
import com.example.iotspringboot.repository.SampleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Service
public class SampleService
{
  private final SampleRepository sampleRepository;

  @Autowired
  public SampleService(SampleRepository sampleRepository) {
    this.sampleRepository = sampleRepository;
  }

  public List<Sample> getSamplesBetweenTimestamps(Instant from, Instant to) {
    return sampleRepository.findByTimeStampBetween(from, to);
  }

  public List<Sample> getSamplesAfterTimestamp(Instant from) {
    return sampleRepository.findByTimeStampAfter(from);
  }

  public List<Sample> getSamplesBeforeTimestamp(Instant to) {
    return sampleRepository.findByTimeStampBefore(to);
  }

  public List<Sample> getAllSamples() {
    return sampleRepository.findAll();
  }

}

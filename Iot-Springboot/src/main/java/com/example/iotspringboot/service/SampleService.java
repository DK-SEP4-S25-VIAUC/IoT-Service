package com.example.iotspringboot.service;

import com.example.iotspringboot.dto.SampleDTO;
import com.example.iotspringboot.mapper.SampleMapper;
import com.example.iotspringboot.model.Sample;
import com.example.iotspringboot.repository.SampleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SampleService
{
  private final SampleRepository sampleRepository;

  @Autowired
  public SampleService(SampleRepository sampleRepository) {
    this.sampleRepository = sampleRepository;
  }

  public List<SampleDTO> getSamplesBetweenTimestamps(Instant from, Instant to) {
    return sampleRepository.findByTimeStampBetween(from, to).stream().map(
        SampleMapper::toDTO).collect(Collectors.toList());
  }

  public List<SampleDTO> getSamplesAfterTimestamp(Instant from) {
    return sampleRepository.findByTimeStampAfter(from).stream().map(
        SampleMapper::toDTO).collect(Collectors.toList());
  }

  public List<SampleDTO> getSamplesBeforeTimestamp(Instant to) {
    return sampleRepository.findByTimeStampBefore(to).stream().map(
        SampleMapper::toDTO).collect(Collectors.toList());
  }

  public List<SampleDTO> getAllSamples() {
    return sampleRepository.findAll().stream().map(
        SampleMapper::toDTO).collect(Collectors.toList());
  }

}

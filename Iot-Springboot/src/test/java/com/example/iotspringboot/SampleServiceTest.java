package com.example.iotspringboot;

import com.example.iotspringboot.dto.SampleDTO;
import com.example.iotspringboot.model.ManualThreshold;
import com.example.iotspringboot.model.Sample;
import com.example.iotspringboot.model.Water;
import com.example.iotspringboot.model.WateringMode;
import com.example.iotspringboot.repository.ManualThresholdRepository;
import com.example.iotspringboot.repository.SampleRepository;
import com.example.iotspringboot.repository.WaterRepository;
import com.example.iotspringboot.repository.WateringModeRepository;
import com.example.iotspringboot.service.SampleService;
import com.example.iotspringboot.mapper.SampleMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.client.RestTemplate;

import java.time.Instant;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class SampleServiceTest {

  @Mock
  private SampleRepository sampleRepository;

  @Mock
  private WateringModeRepository wateringModeRepository;

  @Mock
  private ManualThresholdRepository manualThresholdRepository;

  @Mock
  private WaterRepository waterRepository;

  @Mock
  private RestTemplate restTemplate;

  @InjectMocks
  private SampleService sampleService;

  @BeforeEach
  void setup() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  void testSaveSample_whenBelowLowerBound_shouldTriggerWatering() {
    // Given
    SampleDTO sampleDTO = new SampleDTO();
    sampleDTO.setSoilHumidity(20.0);
    sampleDTO.setAirHumidity(40.0);

    Sample sample = SampleMapper.toEntity(sampleDTO);
    sample.setTimeStamp(Instant.now());

    when(sampleRepository.save(any(Sample.class))).thenReturn(sample);
    when(wateringModeRepository.findById(1L)).thenReturn(Optional.of(wateringMode(true)));
    when(manualThresholdRepository.findFirstByOrderByIdDesc())
        .thenReturn(Optional.of(threshold(25.0, 60.0)));

    // When
    SampleDTO result = sampleService.saveSample(sampleDTO);

    // Then
    assertNotNull(result);
    verify(waterRepository, times(1)).save(any(Water.class));
  }

  @Test
  void testSaveSample_whenAboveLowerBound_shouldNotTriggerWatering() {
    SampleDTO sampleDTO = new SampleDTO();
    sampleDTO.setSoilHumidity(50.0);

    Sample sample = SampleMapper.toEntity(sampleDTO);
    sample.setTimeStamp(Instant.now());

    when(sampleRepository.save(any(Sample.class))).thenReturn(sample);
    when(wateringModeRepository.findById(1L)).thenReturn(Optional.of(wateringMode(true)));
    when(manualThresholdRepository.findFirstByOrderByIdDesc())
        .thenReturn(Optional.of(threshold(25.0, 60.0)));

    SampleDTO result = sampleService.saveSample(sampleDTO);

    assertNotNull(result);
    verify(waterRepository, never()).save(any(Water.class));
  }

  @Test
  void testSaveSample_whenAutomaticWateringDisabled_shouldNotTriggerWatering() {
    SampleDTO sampleDTO = new SampleDTO();
    sampleDTO.setSoilHumidity(15.0);

    Sample sample = SampleMapper.toEntity(sampleDTO);
    sample.setTimeStamp(Instant.now());

    when(sampleRepository.save(any(Sample.class))).thenReturn(sample);
    when(wateringModeRepository.findById(1L)).thenReturn(Optional.of(wateringMode(false)));

    SampleDTO result = sampleService.saveSample(sampleDTO);

    assertNotNull(result);
    verify(waterRepository, never()).save(any(Water.class));
  }

  private WateringMode wateringMode(boolean enabled) {
    WateringMode wm = new WateringMode();
    wm.setId(1);
    wm.setAutomaticWatering(enabled);
    return wm;
  }

  private ManualThreshold threshold(double lower, double upper) {
    ManualThreshold t = new ManualThreshold();
    t.setId(1);
    t.setLowerbound(lower);
    t.setUpperbound(upper);
    return t;
  }
}

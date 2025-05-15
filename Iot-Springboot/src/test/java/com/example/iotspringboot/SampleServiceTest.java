package com.example.iotspringboot;

import com.example.iotspringboot.dto.SampleDTO;
import com.example.iotspringboot.mapper.SampleMapper;
import com.example.iotspringboot.model.ManualThreshold;
import com.example.iotspringboot.model.Sample;
import com.example.iotspringboot.model.Water;
import com.example.iotspringboot.model.WateringMode;
import com.example.iotspringboot.repository.ManualThresholdRepository;
import com.example.iotspringboot.repository.SampleRepository;
import com.example.iotspringboot.repository.WaterRepository;
import com.example.iotspringboot.repository.WateringModeRepository;

import com.example.iotspringboot.service.SampleService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.http.*;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.client.RestTemplate;

import java.time.Instant;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class SampleServiceTest {

  @Mock
  private SampleRepository sampleRepository;

  @Mock
  private WateringModeRepository wateringModeRepository;

  @Mock
  private ManualThresholdRepository manualThresholdRepository;

  @Mock
  private WaterRepository waterRepository;


  @InjectMocks
  private SampleService sampleService;

  @BeforeEach
  public void setup() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  void testSaveSample_WhenBelowLowerBound_ShouldTriggerWateringAndLogWater() {
    // Arrange
    RestTemplate mockRestTemplate = mock(RestTemplate.class);
    when(mockRestTemplate.postForObject(anyString(), any(), eq(String.class)))
        .thenReturn("Mocked Response");

    // Use ReflectionTestUtils to inject the mockRestTemplate into the SampleService
    ReflectionTestUtils.setField(sampleService, "restTemplate", mockRestTemplate);

    // Create a mock SampleDTO object (adjust the values as per your requirements)
    SampleDTO sampleDTO = new SampleDTO();
    sampleDTO.setSoilHumidity(22.0);
    sampleDTO.setAirHumidity(22.0);

    // Act
    sampleService.saveSample(sampleDTO);  // passing the DTO to saveSample

    // Assert
    verify(mockRestTemplate, times(1)).postForObject(anyString(), eq(sampleDTO), eq(String.class));
  }





  @Test
  public void testSaveSample_WhenAboveLowerBound_ShouldNotTriggerWatering() {
    // Given
    SampleDTO sampleDTO = new SampleDTO();
    sampleDTO.setSoilHumidity(50.0); // Above lower bound
    Sample sample = SampleMapper.toEntity(sampleDTO);
    sample.setTimeStamp(Instant.now());

    Sample savedSample = new Sample();
    savedSample.setId(1);
    savedSample.setSoilHumidity(50.0);
    savedSample.setTimeStamp(Instant.now());

    when(sampleRepository.save(any(Sample.class))).thenReturn(savedSample);
    when(wateringModeRepository.findById(1L)).thenReturn(Optional.of(wateringMode(true)));
    when(manualThresholdRepository.findFirstByOrderByIdDesc()).thenReturn(Optional.of(threshold(30.0, 60.0)));

    // When
    SampleDTO result = sampleService.saveSample(sampleDTO);

    // Then
    assertNotNull(result);
    verify(waterRepository, never()).save(any(Water.class)); // No watering triggered
  }

  @Test
  public void testSaveSample_WhenAutomaticWateringDisabled_ShouldNotTriggerWatering() {
    // Given
    SampleDTO sampleDTO = new SampleDTO();
    sampleDTO.setSoilHumidity(20.0); // Below lower bound
    Sample sample = SampleMapper.toEntity(sampleDTO);
    sample.setTimeStamp(Instant.now());

    Sample savedSample = new Sample();
    savedSample.setId(1);
    savedSample.setSoilHumidity(20.0);
    savedSample.setTimeStamp(Instant.now());

    when(sampleRepository.save(any(Sample.class))).thenReturn(savedSample);
    when(wateringModeRepository.findById(1L)).thenReturn(Optional.of(wateringMode(false)));

    // When
    SampleDTO result = sampleService.saveSample(sampleDTO);

    // Then
    assertNotNull(result);
    verify(waterRepository, never()).save(any(Water.class));
  }

  private WateringMode wateringMode(boolean automatic) {
    WateringMode mode = new WateringMode();
    mode.setId(1);
    mode.setAutomaticWatering(automatic);
    return mode;
  }

  private ManualThreshold threshold(double lower, double upper) {
    ManualThreshold threshold = new ManualThreshold();
    threshold.setId(1);
    threshold.setLowerbound(lower);
    threshold.setUpperbound(upper);
    return threshold;
  }
}

package com.example.iotspringboot;

import com.example.iotspringboot.dto.CreateSoilHumidityDTO;
import com.example.iotspringboot.dto.SoilHumidityDTO;
import com.example.iotspringboot.mapper.SoilHumidityMapper;
import com.example.iotspringboot.model.SoilHumidity;
import com.example.iotspringboot.repository.SoilHumidityRepository;
import com.example.iotspringboot.service.SoilHumidityService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.Instant;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class SoilHumidityServiceTest {

  private SoilHumidityRepository soilHumidityRepository;
  private SoilHumidityService soilHumidityService;

  @BeforeEach
  void setUp() {
    soilHumidityRepository = mock(SoilHumidityRepository.class);
    soilHumidityService = new SoilHumidityService(soilHumidityRepository);
  }

  @Test
  void getLatestSoilHumidity_whenDataExists_returnsDto() {
    SoilHumidity entity = new SoilHumidity();
    entity.setId(1);
    entity.setSoilHumidityValue(42.0);
    entity.setTimeStamp(Instant.now());

    when(soilHumidityRepository.findTopByOrderByTimeStampDesc()).thenReturn(entity);

    SoilHumidityDTO result = soilHumidityService.getLatestSoilHumidity();
    assertNotNull(result);
    assertEquals(42.0, result.getSoil_humidity_value());
  }

  @Test
  void getLatestSoilHumidity_whenNoDataExists_returnsNull() {
    when(soilHumidityRepository.findTopByOrderByTimeStampDesc()).thenReturn(null);
    assertNull(soilHumidityService.getLatestSoilHumidity());
  }

  @Test
  void getAllSoilHumidities_whenDataExists_returnsDtoList() {
    SoilHumidity entity = new SoilHumidity();
    entity.setId(1);
    entity.setSoilHumidityValue(50.0);
    entity.setTimeStamp(Instant.now());

    when(soilHumidityRepository.findAll()).thenReturn(List.of(entity));

    List<SoilHumidityDTO> result = soilHumidityService.getAllSoilHumidities();
    assertEquals(1, result.size());
    assertEquals(50.0, result.get(0).getSoil_humidity_value());
  }

  @Test
  void getAllSoilHumidities_whenNoDataExists_returnsEmptyList() {
    when(soilHumidityRepository.findAll()).thenReturn(Collections.emptyList());
    List<SoilHumidityDTO> result = soilHumidityService.getAllSoilHumidities();
    assertTrue(result.isEmpty());
  }

  @Test
  void saveSoilHumidity_validInput_savesAndReturnsDto() {
    CreateSoilHumidityDTO input = new CreateSoilHumidityDTO();
    input.setSoil_humidity_value(60.0);

    SoilHumidity saved = new SoilHumidity();
    saved.setId(5);
    saved.setSoilHumidityValue(60.0);
    saved.setTimeStamp(Instant.now());

    when(soilHumidityRepository.save(any())).thenReturn(saved);

    SoilHumidityDTO result = soilHumidityService.saveSoilHumidity(input);

    assertNotNull(result);
    assertEquals(60.0, result.getSoil_humidity_value());
  }

  @Test
  void getSoilHumidityAfterTimestamp_whenNoMatch_returnsEmptyList() {
    Instant from = Instant.now().minusSeconds(3600);
    when(soilHumidityRepository.findByTimeStampAfter(from)).thenReturn(Collections.emptyList());
    List<SoilHumidityDTO> result = soilHumidityService.getSoilHumidityAfterTimestamp(from);
    assertTrue(result.isEmpty());
  }

  @Test
  void getSoilHumidityBeforeTimestamp_whenMatchesExist_returnsList() {
    Instant to = Instant.now();
    SoilHumidity mock = new SoilHumidity();
    mock.setId(2);
    mock.setSoilHumidityValue(35.5);
    mock.setTimeStamp(to.minusSeconds(10));

    when(soilHumidityRepository.findByTimeStampBefore(to)).thenReturn(List.of(mock));
    List<SoilHumidityDTO> result = soilHumidityService.getSoilHumidityBeforeTimestamp(to);

    assertEquals(1, result.size());
    assertEquals(35.5, result.get(0).getSoil_humidity_value());
  }

  @Test
  void getSoilHumiditiesBetweenTimestamps_validRange_returnsExpectedResults() {
    Instant from = Instant.now().minusSeconds(5000);
    Instant to = Instant.now();

    SoilHumidity mock = new SoilHumidity();
    mock.setId(3);
    mock.setSoilHumidityValue(28.0);
    mock.setTimeStamp(Instant.now().minusSeconds(1000));

    when(soilHumidityRepository.findByTimeStampBetween(from, to)).thenReturn(List.of(mock));

    List<SoilHumidityDTO> result = soilHumidityService.getSoilHumiditiesBetweenTimestamps(from, to);
    assertEquals(1, result.size());
    assertEquals(28.0, result.get(0).getSoil_humidity_value());
  }

  @Test
  void getSoilHumiditiesBetweenTimestamps_invalidRange_returnsEmptyList() {
    Instant from = Instant.now();
    Instant to = from.minusSeconds(3600); // from > to

    when(soilHumidityRepository.findByTimeStampBetween(from, to)).thenReturn(Collections.emptyList());

    List<SoilHumidityDTO> result = soilHumidityService.getSoilHumiditiesBetweenTimestamps(from, to);
    assertTrue(result.isEmpty());
  }
}

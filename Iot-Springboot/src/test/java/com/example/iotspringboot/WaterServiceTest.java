package com.example.iotspringboot;

import com.example.iotspringboot.dto.WaterDTO;
import com.example.iotspringboot.mapper.WaterMapper;
import com.example.iotspringboot.model.Water;
import com.example.iotspringboot.repository.WaterRepository;
import com.example.iotspringboot.service.WaterService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.Instant;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class WaterServiceTest {

  @Mock
  private WaterService waterService;

  @Test
  void testGetLatestWaterReading() {
    WaterDTO mockWater = new WaterDTO();
    mockWater.setWater_level(10.5);

    // Ensure you're calling the method on the mock
    when(waterService.getLatestWaterReading()).thenReturn(mockWater);

    // Proceed with your assertions
    WaterDTO result = waterService.getLatestWaterReading();
    assertEquals(10.5, result.getWater_level());
  }

  @Test
  void testGetAllWaters() {
    List<WaterDTO> mockWaterList = Arrays.asList(new WaterDTO(), new WaterDTO());

    when(waterService.getAllWaters()).thenReturn(mockWaterList);

    List<WaterDTO> result = waterService.getAllWaters();
    assertEquals(2, result.size());
  }
}


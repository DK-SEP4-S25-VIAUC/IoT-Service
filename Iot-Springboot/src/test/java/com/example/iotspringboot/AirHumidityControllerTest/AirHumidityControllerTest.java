package com.example.iotspringboot.AirHumidityControllerTest;

import com.example.iotspringboot.controllers.AirHumidityController;
import com.example.iotspringboot.dto.AirHumidityDTO;
import com.example.iotspringboot.service.AirHumidityService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AirHumidityControllerTest {

    private AirHumidityService airHumidityService;
    private AirHumidityController airHumidityController;

    @BeforeEach
    void setUp() {
        airHumidityService = mock(AirHumidityService.class);
        airHumidityController = new AirHumidityController(airHumidityService);
    }

    @Test
    void getLatestAirHumidity_shouldReturnLatestReading() {
        // Arrange
        AirHumidityDTO mockDto = new AirHumidityDTO();
        mockDto.setId(1);
        mockDto.setAirHumidityValue(55.0);
        mockDto.setTime_stamp(Instant.now());

        when(airHumidityService.getLatestAirHumidity()).thenReturn(mockDto);

        // Act
        AirHumidityDTO result = airHumidityController.getLatestAirHumidity();

        // Assert
        assertNotNull(result);
        assertEquals(1, result.getId());
        assertEquals(55.0, result.getAirHumidityValue());
        assertNotNull(result.getTime_stamp());
        verify(airHumidityService, times(1)).getLatestAirHumidity();
    }

    @Test
    void getAllAirHumidityReadings_shouldReturnAllReadings() {
        // Arrange
        AirHumidityDTO dto1 = new AirHumidityDTO();
        dto1.setId(1);
        dto1.setAirHumidityValue(55.0);
        dto1.setTime_stamp(Instant.now());

        AirHumidityDTO dto2 = new AirHumidityDTO();
        dto2.setId(2);
        dto2.setAirHumidityValue(60.0);
        dto2.setTime_stamp(Instant.now().minusSeconds(600));

        List<AirHumidityDTO> mockList = Arrays.asList(dto1, dto2);
        when(airHumidityService.getAllAirHumidities()).thenReturn(mockList);

        // Act
        List<AirHumidityDTO> result = airHumidityController.getAllAirHumidityReadings();

        // Assert
        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals(55.0, result.get(0).getAirHumidityValue());
        assertEquals(60.0, result.get(1).getAirHumidityValue());
        verify(airHumidityService, times(1)).getAllAirHumidities();
    }
}

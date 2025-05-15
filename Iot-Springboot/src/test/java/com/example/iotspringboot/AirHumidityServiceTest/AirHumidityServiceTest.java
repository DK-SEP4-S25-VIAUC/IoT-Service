package com.example.iotspringboot.AirHumidityServiceTest;

import com.example.iotspringboot.dto.AirHumidityDTO;
import com.example.iotspringboot.model.AirHumidity;
import com.example.iotspringboot.repository.AirHumidityRepository;
import com.example.iotspringboot.service.AirHumidityService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AirHumidityServiceTest {

    private AirHumidityRepository airHumidityRepository;
    private AirHumidityService airHumidityService;

    @BeforeEach
    void setUp() {
        airHumidityRepository = mock(AirHumidityRepository.class);
        airHumidityService = new AirHumidityService(airHumidityRepository);
    }

    @Test
    void getLatestAirHumidity_shouldReturnLatestReading() {
        // Arrange
        AirHumidity mockEntity = new AirHumidity();
        mockEntity.setId(1);
        mockEntity.setAirHumidityValue(45.5);
        mockEntity.setTimeStamp(Instant.now());

        when(airHumidityRepository.findTopByOrderByTimeStampDesc()).thenReturn(mockEntity);

        // Act
        AirHumidityDTO result = airHumidityService.getLatestAirHumidity();

        // Assert
        assertNotNull(result);
        assertEquals(1, result.getId());
        assertEquals(45.5, result.getAirHumidityValue());
        assertNotNull(result.getTime_stamp());

        verify(airHumidityRepository, times(1)).findTopByOrderByTimeStampDesc();
    }

    @Test
    void getAllAirHumidities_shouldReturnAllReadings() {
        // Arrange
        AirHumidity h1 = new AirHumidity();
        h1.setId(1);
        h1.setAirHumidityValue(40.0);
        h1.setTimeStamp(Instant.now());

        AirHumidity h2 = new AirHumidity();
        h2.setId(2);
        h2.setAirHumidityValue(50.5);
        h2.setTimeStamp(Instant.now().minusSeconds(600));

        List<AirHumidity> entities = Arrays.asList(h1, h2);
        when(airHumidityRepository.findAll()).thenReturn(entities);

        // Act
        List<AirHumidityDTO> result = airHumidityService.getAllAirHumidities();

        // Assert
        assertEquals(2, result.size());
        assertEquals(40.0, result.get(0).getAirHumidityValue());
        assertEquals(50.5, result.get(1).getAirHumidityValue());

        verify(airHumidityRepository, times(1)).findAll();
    }

    @Test
    void getLatestAirHumidity_whenNoData_shouldReturnNull() {
        // Arrange
        when(airHumidityRepository.findTopByOrderByTimeStampDesc()).thenReturn(null);

        // Act
        AirHumidityDTO result = airHumidityService.getLatestAirHumidity();

        // Assert
        assertNull(result);

        verify(airHumidityRepository, times(1)).findTopByOrderByTimeStampDesc();
    }
}

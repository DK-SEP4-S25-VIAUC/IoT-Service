package com.example.iotspringboot.LightControllerTest;

import com.example.iotspringboot.controllers.LightController;
import com.example.iotspringboot.dto.LightDTO;
import com.example.iotspringboot.service.LightService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class LightControllerTest {

    private LightService lightService;
    private LightController lightController;

    @BeforeEach
    void setUp() {
        lightService = mock(LightService.class);
        lightController = new LightController(lightService);
    }

    @Test
    void getLatestLightReading_shouldReturnLatestReading() {
        // Arrange
        LightDTO mockDto = new LightDTO();
        mockDto.setId(1);
        mockDto.setLight_value(75.0);
        mockDto.setTime_stamp(Instant.now());

        when(lightService.getLatestLightReading()).thenReturn(mockDto);

        // Act
        LightDTO result = lightController.getLatestLightReading();

        // Assert
        assertNotNull(result);
        assertEquals(1, result.getId());
        assertEquals(75.0, result.getLight_value());
        assertNotNull(result.getTime_stamp());

        verify(lightService, times(1)).getLatestLightReading();
    }

    @Test
    void getAllLightReadings_shouldReturnAllReadings() {
        // Arrange
        LightDTO dto1 = new LightDTO();
        dto1.setId(1);
        dto1.setLight_value(75.0);
        dto1.setTime_stamp(Instant.now());

        LightDTO dto2 = new LightDTO();
        dto2.setId(2);
        dto2.setLight_value(80.5);
        dto2.setTime_stamp(Instant.now().minusSeconds(300));

        List<LightDTO> mockList = Arrays.asList(dto1, dto2);
        when(lightService.getAllLights()).thenReturn(mockList);

        // Act
        List<LightDTO> result = lightController.getAllLightReadings();

        // Assert
        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals(75.0, result.get(0).getLight_value());
        assertEquals(80.5, result.get(1).getLight_value());

        verify(lightService, times(1)).getAllLights();
    }
}

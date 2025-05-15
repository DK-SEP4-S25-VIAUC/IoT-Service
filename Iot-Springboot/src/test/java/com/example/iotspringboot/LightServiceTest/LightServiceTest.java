package com.example.iotspringboot.LightServiceTest;

import com.example.iotspringboot.dto.LightDTO;
import com.example.iotspringboot.model.Light;
import com.example.iotspringboot.repository.LightRepository;
import com.example.iotspringboot.service.LightService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.Instant;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class LightServiceTest {

    @Mock
    private LightRepository lightRepository;

    @InjectMocks
    private LightService lightService;

    private Light light1;
    private Light light2;

    @BeforeEach
    void setUp() {
        // Initialiser nogle test-objekter
        light1 = new Light();
        light1.setId(1);
        light1.setLightValue(100.5);
        light1.setTimeStamp(Instant.parse("2025-05-14T10:15:30.00Z"));

        light2 = new Light();
        light2.setId(2);
        light2.setLightValue(150.75);
        light2.setTimeStamp(Instant.parse("2025-05-15T11:20:35.00Z"));
    }

    @Test
    void testGetLatestLightReading() {
        // Arrange
        when(lightRepository.findTopByOrderByTimeStampDesc()).thenReturn(light2);

        // Act
        LightDTO latest = lightService.getLatestLightReading();

        // Assert
        assertThat(latest).isNotNull();
        assertThat(latest.getId()).isEqualTo(light2.getId());
        assertThat(latest.getLight_value()).isEqualTo(light2.getLightValue());
        assertThat(latest.getTime_stamp()).isEqualTo(light2.getTimeStamp());

        verify(lightRepository, times(1)).findTopByOrderByTimeStampDesc();
    }

    @Test
    void testGetAllLights() {
        // Arrange
        when(lightRepository.findAll()).thenReturn(Arrays.asList(light1, light2));

        // Act
        List<LightDTO> allLights = lightService.getAllLights();

        // Assert
        assertThat(allLights).isNotNull();
        assertThat(allLights.size()).isEqualTo(2);

        assertThat(allLights.get(0).getId()).isEqualTo(light1.getId());
        assertThat(allLights.get(1).getId()).isEqualTo(light2.getId());

        verify(lightRepository, times(1)).findAll();
    }

    @Test
    void testGetLatestLightReading_whenNoData() {
        // Arrange
        when(lightRepository.findTopByOrderByTimeStampDesc()).thenReturn(null);

        // Act
        LightDTO latest = lightService.getLatestLightReading();

        // Assert
        assertThat(latest).isNull();

        verify(lightRepository, times(1)).findTopByOrderByTimeStampDesc();
    }
}

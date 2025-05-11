package com.example.iotspringboot.TemperatureServiceTest;

import com.example.iotspringboot.dto.TemperatureDTO;
import com.example.iotspringboot.mapper.TemperatureMapper;
import com.example.iotspringboot.model.Temperature;
import com.example.iotspringboot.repository.TemperatureRepository;
import com.example.iotspringboot.service.TemperatureService;
import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TemperatureServiceTest {

    private final TemperatureRepository temperatureRepository = mock(TemperatureRepository.class);
    private final TemperatureService temperatureService = new TemperatureService(temperatureRepository);

    @Test
    void getLatestTemperature_returnsCorrectDTO() {
        Temperature temp = new Temperature();
        temp.setId(1);
        temp.setTimeStamp(Instant.now());
        temp.setTemperatureValue(22.5);

        when(temperatureRepository.findTopByOrderByTimeStampDesc()).thenReturn(temp);

        TemperatureDTO result = temperatureService.getLatestTemperature();

        assertEquals(1, result.getId());
        assertEquals(22.5, result.getTemperature_value());
        assertNotNull(result.getTime_stamp());
    }

    @Test
    void getAllTemperatures_returnsAllMappedDTOs() {
        Temperature temp1 = new Temperature();
        temp1.setId(1);
        temp1.setTimeStamp(Instant.now());
        temp1.setTemperatureValue(22.5);

        Temperature temp2 = new Temperature();
        temp2.setId(2);
        temp2.setTimeStamp(Instant.now());
        temp2.setTemperatureValue(23.5);

        when(temperatureRepository.findAll()).thenReturn(Arrays.asList(temp1, temp2));

        List<TemperatureDTO> result = temperatureService.getAllTemperatures();

        assertEquals(2, result.size());
        assertEquals(23.5, result.get(1).getTemperature_value());
    }
}

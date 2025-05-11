package com.example.iotspringboot.TemperatureControllerTest;

import com.example.iotspringboot.controllers.TemperatureController;
import com.example.iotspringboot.dto.TemperatureDTO;
import com.example.iotspringboot.service.TemperatureService;
import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TemperatureControllerTest {

    private final TemperatureService temperatureService = mock(TemperatureService.class);
    private final TemperatureController controller = new TemperatureController(temperatureService);

    @Test
    void getLatestReading_returnsCorrectDTO() {
        TemperatureDTO dto = new TemperatureDTO();
        dto.setId(1);
        dto.setTemperature_value(22.0);
        dto.setTime_stamp(Instant.now());

        when(temperatureService.getLatestTemperature()).thenReturn(dto);

        TemperatureDTO result = controller.getLatestReading();

        assertEquals(1, result.getId());
        assertEquals(22.0, result.getTemperature_value());
    }

    @Test
    void getAllTemperatureReadings_returnsListOfDTOs() {
        TemperatureDTO dto1 = new TemperatureDTO();
        dto1.setId(1);
        dto1.setTemperature_value(21.5);
        dto1.setTime_stamp(Instant.now());

        TemperatureDTO dto2 = new TemperatureDTO();
        dto2.setId(2);
        dto2.setTemperature_value(22.5);
        dto2.setTime_stamp(Instant.now());

        when(temperatureService.getAllTemperatures()).thenReturn(List.of(dto1, dto2));

        List<TemperatureDTO> result = controller.getAllTemperatureReadings();

        assertEquals(2, result.size());
        assertEquals(22.5, result.get(1).getTemperature_value());
    }
}

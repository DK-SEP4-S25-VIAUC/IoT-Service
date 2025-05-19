package com.example.iotspringboot.TemperatureControllerTest;

import com.example.iotspringboot.controllers.TemperatureController;
import com.example.iotspringboot.dto.TemperatureDTO;
import com.example.iotspringboot.service.TemperatureService;
import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TemperatureControllerTest {

    private final TemperatureService temperatureService = mock(TemperatureService.class);
    private final TemperatureController controller = new TemperatureController(temperatureService);

    @Test
    void getLatestTemperature_returnsCorrectDTO() {
        TemperatureDTO dto = new TemperatureDTO();
        dto.setId(1);
        dto.setTemperature_value(22.0);
        dto.setTime_stamp(Instant.now());

        when(temperatureService.getLatestTemperature()).thenReturn(dto);

        Map<String, Object> result = controller.getLatestTemperature();
        TemperatureDTO actual = (TemperatureDTO) result.get("TemperatureDTO");

        assertEquals(1, actual.getId());
        assertEquals(22.0, actual.getTemperature_value());
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

        Instant from = Instant.now().minusSeconds(3600);
        Instant to = Instant.now();

        when(temperatureService.getTemperaturesBetweenTimestamps(from, to)).thenReturn(List.of(dto1, dto2));

        Map<String, Object> result = controller.getAllTemperatureReadings(from, to);

        @SuppressWarnings("unchecked")
        List<Map<String, Object>> list = (List<Map<String, Object>>) result.get("list");

        assertEquals(2, list.size());

        TemperatureDTO extracted = (TemperatureDTO) list.get(1).get("TemperatureDTO");
        assertEquals(22.5, extracted.getTemperature_value());
    }



}

package com.example.iotspringboot.SoilHumidityControllerTest;

import com.example.iotspringboot.controllers.SoilHumidityController;
import com.example.iotspringboot.dto.CreateSoilHumidityDTO;
import com.example.iotspringboot.dto.SoilHumidityDTO;
import com.example.iotspringboot.service.ManualThresholdService;
import com.example.iotspringboot.service.SoilHumidityService;
import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class SoilHumidityControllerTest {

    private final SoilHumidityService soilHumidityService = mock(SoilHumidityService.class);
    private final ManualThresholdService manualThresholdService = mock(ManualThresholdService.class);
    private final SoilHumidityController controller = new SoilHumidityController(soilHumidityService, manualThresholdService);

    @Test
    void getLatestSoilHumidity_returnsCorrectDTO() {
        SoilHumidityDTO dto = new SoilHumidityDTO();
        dto.setId(1);
        dto.setSoil_humidity_value(40.5);
        dto.setTime_stamp(Instant.now());

        when(soilHumidityService.getLatestSoilHumidity()).thenReturn(dto);

        Map<String, Object> result = controller.getLatestSoilHumidity();
        SoilHumidityDTO actual = (SoilHumidityDTO) result.get("SoilHumidityDTO");

        assertEquals(dto.getId(), actual.getId());
        assertEquals(dto.getSoil_humidity_value(), actual.getSoil_humidity_value());
    }

    @Test
    void getAllSoilHumidityReadings_returnsListOfDTOs() {
        SoilHumidityDTO dto1 = new SoilHumidityDTO();
        dto1.setId(1);
        dto1.setSoil_humidity_value(32.0);
        dto1.setTime_stamp(Instant.now());

        SoilHumidityDTO dto2 = new SoilHumidityDTO();
        dto2.setId(2);
        dto2.setSoil_humidity_value(45.0);
        dto2.setTime_stamp(Instant.now());

        when(soilHumidityService.getAllSoilHumidities()).thenReturn(List.of(dto1, dto2));

        Map<String, Object> result = controller.getSoilHumidity(null, null);
        @SuppressWarnings("unchecked")
        List<Map<String, Object>> list = (List<Map<String, Object>>) result.get("list");

        assertEquals(2, list.size());

        SoilHumidityDTO extracted = (SoilHumidityDTO) list.get(1).get("SoilHumidityDTO");
        assertEquals(45.0, extracted.getSoil_humidity_value());
    }

    @Test
    void saveSoilHumidity_returnsSavedDTO() {
        CreateSoilHumidityDTO input = new CreateSoilHumidityDTO();
        input.setSoil_humidity_value(55.5);

        SoilHumidityDTO output = new SoilHumidityDTO();
        output.setId(3);
        output.setSoil_humidity_value(55.5);
        output.setTime_stamp(Instant.now());

        when(soilHumidityService.saveSoilHumidity(input)).thenReturn(output);

        SoilHumidityDTO actual = controller.saveSoilHumidity(input);
        assertEquals(55.5, actual.getSoil_humidity_value());
    }
}

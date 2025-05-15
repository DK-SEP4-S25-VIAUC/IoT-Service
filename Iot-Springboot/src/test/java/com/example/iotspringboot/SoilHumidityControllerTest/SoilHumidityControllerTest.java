package com.example.iotspringboot.SoilHumidityControllerTest;

import com.example.iotspringboot.controllers.SoilHumidityController;
import com.example.iotspringboot.dto.CreateManualThresholdDTO;
import com.example.iotspringboot.dto.CreateSoilHumidityDTO;
import com.example.iotspringboot.dto.SoilHumidityDTO;
import com.example.iotspringboot.service.ManualThresholdService;
import com.example.iotspringboot.service.SoilHumidityService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.Instant;
import java.time.ZonedDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class SoilHumidityControllerTest {

    private SoilHumidityService soilHumidityService;
    private ManualThresholdService manualThresholdService;
    private SoilHumidityController controller;

    @BeforeEach
    public void setUp() {
        soilHumidityService = Mockito.mock(SoilHumidityService.class);
        manualThresholdService = Mockito.mock(ManualThresholdService.class);
        controller = new SoilHumidityController(soilHumidityService, manualThresholdService);
    }

    @Test
    public void testGetLatestSoilHumidity() {
        SoilHumidityDTO dto = new SoilHumidityDTO();
        dto.setId(1);
        dto.setSoil_humidity_value(45.0);
        dto.setTime_stamp(ZonedDateTime.now());

        when(soilHumidityService.getLatestSoilHumidity()).thenReturn(dto);

        SoilHumidityDTO result = controller.getLatestSoilHumidity();
        assertEquals(45.0, result.getSoil_humidity_value());
        verify(soilHumidityService, times(1)).getLatestSoilHumidity();
    }

    @Test
    public void testGetAllSoilHumidities_NoParams() {
        SoilHumidityDTO dto = new SoilHumidityDTO();
        dto.setId(1);
        dto.setSoil_humidity_value(33.0);

        when(soilHumidityService.getAllSoilHumidities()).thenReturn(List.of(dto));

        List<SoilHumidityDTO> result = controller.getSoilHumidity(null, null);
        assertEquals(1, result.size());
        verify(soilHumidityService, times(1)).getAllSoilHumidities();
    }

    @Test
    public void testGetSoilHumidity_FromTo() {
        Instant from = Instant.now().minusSeconds(3600);
        Instant to = Instant.now();

        SoilHumidityDTO dto = new SoilHumidityDTO();
        dto.setSoil_humidity_value(55.5);

        when(soilHumidityService.getSoilHumiditiesBetweenTimestamps(from, to)).thenReturn(List.of(dto));

        List<SoilHumidityDTO> result = controller.getSoilHumidity(from, to);
        assertEquals(1, result.size());
        verify(soilHumidityService, times(1)).getSoilHumiditiesBetweenTimestamps(from, to);
    }

    @Test
    public void testSaveSoilHumidity() {
        CreateSoilHumidityDTO request = new CreateSoilHumidityDTO();
        request.setSoil_humidity_value(65.5);

        SoilHumidityDTO response = new SoilHumidityDTO();
        response.setId(1);
        response.setSoil_humidity_value(65.5);

        when(soilHumidityService.saveSoilHumidity(request)).thenReturn(response);

        SoilHumidityDTO result = controller.saveSoilHumidity(request);
        assertEquals(65.5, result.getSoil_humidity_value());
        verify(soilHumidityService, times(1)).saveSoilHumidity(request);
    }

    @Test
    public void testSetSoilHumidityThreshold() {
        CreateManualThresholdDTO input = new CreateManualThresholdDTO();
        input.setLowerbound(20);
        input.setUpperbound(80);

        when(manualThresholdService.setThreshold(input)).thenReturn(input);

        CreateManualThresholdDTO result = controller.setSoilHumidityThreshold(input);
        assertEquals(20, result.getLowerbound());
        assertEquals(80, result.getUpperbound());
        verify(manualThresholdService, times(1)).setThreshold(input);
    }

    @Test
    public void testGetSoilHumidityThreshold() {
        CreateManualThresholdDTO threshold = new CreateManualThresholdDTO();
        threshold.setLowerbound(10);
        threshold.setUpperbound(70);

        when(manualThresholdService.getThreshold()).thenReturn(threshold);

        CreateManualThresholdDTO result = controller.getSoilHumidityThreshold();
        assertEquals(10, result.getLowerbound());
        assertEquals(70, result.getUpperbound());
        verify(manualThresholdService, times(1)).getThreshold();
    }
}

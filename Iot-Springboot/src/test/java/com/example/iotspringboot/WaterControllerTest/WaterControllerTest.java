package com.example.iotspringboot.WaterControllerTest;

import com.example.iotspringboot.controllers.WaterController;
import com.example.iotspringboot.dto.WaterDTO;
import com.example.iotspringboot.service.WaterService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class WaterControllerTest {

    private WaterService waterService;
    private WaterController controller;

    @BeforeEach
    public void setUp() {
        waterService = mock(WaterService.class);
        controller = new WaterController(waterService);
    }

    @Test
    public void testGetLatestWaterReading() {
        WaterDTO dto = new WaterDTO();
        dto.setId(1);
        dto.setTimestamp(Instant.now());
        dto.setWater_level(30.5);
        dto.setWatered_amount(12.3);

        when(waterService.getLatestWaterReading()).thenReturn(dto);

        WaterDTO result = controller.getLatestWaterReading();

        assertEquals(1, result.getId());
        assertEquals(30.5, result.getWater_level());
        assertEquals(12.3, result.getWatered_amount());
        verify(waterService, times(1)).getLatestWaterReading();
    }

    @Test
    public void testGetAllWaterReadings() {
        WaterDTO dto1 = new WaterDTO();
        dto1.setId(1);
        dto1.setWater_level(40.0);
        dto1.setWatered_amount(10.0);
        dto1.setTimestamp(Instant.now());

        WaterDTO dto2 = new WaterDTO();
        dto2.setId(2);
        dto2.setWater_level(35.5);
        dto2.setWatered_amount(9.5);
        dto2.setTimestamp(Instant.now());

        when(waterService.getAllWaters()).thenReturn(List.of(dto1, dto2));

        List<WaterDTO> result = controller.getAllWaterReadings();

        assertEquals(2, result.size());
        assertEquals(40.0, result.get(0).getWater_level());
        assertEquals(35.5, result.get(1).getWater_level());
        verify(waterService, times(1)).getAllWaters();
    }
}

package com.example.iotspringboot.WateringModeControllerTest;

import com.example.iotspringboot.controllers.WateringModeController;
import com.example.iotspringboot.dto.WateringModeDTO;
import com.example.iotspringboot.service.WateringModeService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(WateringModeController.class)
@Import(WateringModeControllerTest.WateringModeTestConfig.class)
public class WateringModeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private WateringModeService wateringModeService;

    @Autowired
    private ObjectMapper objectMapper;

    private WateringModeDTO modeDto;

    @BeforeEach
    public void setup() {
        modeDto = new WateringModeDTO();
        modeDto.setAutomaticWatering(true);
    }

    @Test
    public void testGetMode_ReturnsAutomaticTrue() throws Exception {
        when(wateringModeService.getMode()).thenReturn(modeDto);

        mockMvc.perform(get("/watering/mode"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.automatic_watering").value(true));
    }

    @Test
    public void testUpdateMode_ReturnsUpdatedMode() throws Exception {
        when(wateringModeService.setMode(Mockito.any(WateringModeDTO.class))).thenReturn(modeDto);

        mockMvc.perform(put("/watering/mode")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(modeDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.automatic_watering").value(true));
    }

    // Testkonfiguration med manuel mock
    @TestConfiguration
    static class WateringModeTestConfig {

        @Bean
        public WateringModeService wateringModeService() {
            return Mockito.mock(WateringModeService.class);
        }
    }
}

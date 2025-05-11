package com.example.iotspringboot.SoilHumidityControllerTest;

import com.example.iotspringboot.controllers.SoilHumidityController;
import com.example.iotspringboot.dto.CreateSoilHumidityDTO;
import com.example.iotspringboot.dto.SoilHumidityDTO;
import com.example.iotspringboot.service.SoilHumidityService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.ZonedDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(SoilHumidityController.class)
@Import(SoilHumidityControllerTest.MockConfig.class)
class SoilHumidityControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private SoilHumidityService soilHumidityService;

    @Autowired
    private ObjectMapper objectMapper;

    @Configuration
    static class MockConfig {
        @Bean
        public SoilHumidityService soilHumidityService() {
            return Mockito.mock(SoilHumidityService.class);
        }
    }

    @Test
    void testGetLatestSoilHumidity() throws Exception {
        SoilHumidityDTO dto = new SoilHumidityDTO();
        dto.setId(1);
        dto.setSoil_humidity_value(40.5);
        dto.setTime_stamp(ZonedDateTime.now());

        when(soilHumidityService.getLatestSoilHumidity()).thenReturn(dto);

        mockMvc.perform(get("/soilhumidity/latest"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(dto.getId()))
                .andExpect(jsonPath("$.soil_humidity_value").value(dto.getSoil_humidity_value()));
    }

    @Test
    void testGetAllSoilHumidityReadings() throws Exception {
        SoilHumidityDTO dto1 = new SoilHumidityDTO();
        dto1.setId(1);
        dto1.setSoil_humidity_value(32.0);
        dto1.setTime_stamp(ZonedDateTime.now());

        SoilHumidityDTO dto2 = new SoilHumidityDTO();
        dto2.setId(2);
        dto2.setSoil_humidity_value(45.0);
        dto2.setTime_stamp(ZonedDateTime.now());

        when(soilHumidityService.getAllSoilHumidities()).thenReturn(List.of(dto1, dto2));

        mockMvc.perform(get("/soilhumidity"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(2));
    }

    @Test
    void testSaveSoilHumidity() throws Exception {
        CreateSoilHumidityDTO input = new CreateSoilHumidityDTO();
        input.setSoil_humidity_value(55.5);

        SoilHumidityDTO output = new SoilHumidityDTO();
        output.setId(3);
        output.setSoil_humidity_value(55.5);
        output.setTime_stamp(ZonedDateTime.now());

        when(soilHumidityService.saveSoilHumidity(input)).thenReturn(output);

        mockMvc.perform(post("/soilhumidity")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(input)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.soil_humidity_value").value(55.5));
    }
}

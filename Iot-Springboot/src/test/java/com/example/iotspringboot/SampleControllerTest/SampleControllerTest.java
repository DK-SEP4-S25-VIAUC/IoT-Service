package com.example.iotspringboot.SampleControllerTest;

import com.example.iotspringboot.controllers.SampleController;
import com.example.iotspringboot.dto.SampleDTO;
import com.example.iotspringboot.service.SampleService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.Instant;
import java.util.List;
import java.util.Map;

import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(SampleController.class)
@Import(SampleControllerTest.TestConfig.class)
public class SampleControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private SampleService sampleService;

    private SampleDTO sampleDTO;

    @BeforeEach
    public void setUp() {
        sampleDTO = new SampleDTO();
        sampleDTO.setId(1);
        sampleDTO.setSoilHumidity(20.0);
        sampleDTO.setAirHumidity(40.0);
        sampleDTO.setAirTemperature(25.0);
        sampleDTO.setLightValue(800.0);
        sampleDTO.setTimestamp(Instant.parse("2024-01-01T10:00:00Z"));
        sampleDTO.setLower_threshold(15.0);
    }

    @Test
    public void testGetAllSamples() throws Exception {
        when(sampleService.getAllSamples()).thenReturn(List.of(sampleDTO));

        mockMvc.perform(get("/sample"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.response.list", hasSize(1)))
                .andExpect(jsonPath("$.response.list[0].SampleDTO.id").value(1))
                .andExpect(jsonPath("$.response.list[0].SampleDTO.soil_humidity").value(20.0));
    }

    @Test
    public void testGetSamplesWithFromAndTo() throws Exception {
        Instant from = Instant.parse("2023-12-01T00:00:00Z");
        Instant to = Instant.parse("2024-12-31T23:59:59Z");

        when(sampleService.getSamplesBetweenTimestamps(from, to)).thenReturn(List.of(sampleDTO));

        mockMvc.perform(get("/sample")
                        .param("from", from.toString())
                        .param("to", to.toString()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.response.list", hasSize(1)))
                .andExpect(jsonPath("$.response.list[0].SampleDTO.air_humidity").value(40.0));
    }

    @Test
    public void testCreateSample() throws Exception {
        when(sampleService.saveSample(Mockito.any(SampleDTO.class))).thenReturn(sampleDTO);

        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(sampleDTO);

        mockMvc.perform(post("/sample")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.soil_humidity").value(20.0))
                .andExpect(jsonPath("$.air_temperature").value(25.0));
    }

    @Configuration
    static class TestConfig {
        @Bean
        public SampleService sampleService() {
            return Mockito.mock(SampleService.class);
        }
    }
}

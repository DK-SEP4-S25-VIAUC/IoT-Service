package com.example.iotspringboot.TemperatureMapperTest;

import com.example.iotspringboot.dto.TemperatureDTO;
import com.example.iotspringboot.mapper.TemperatureMapper;
import com.example.iotspringboot.model.Temperature;
import org.junit.jupiter.api.Test;

import java.time.Instant;

import static org.junit.jupiter.api.Assertions.*;

class TemperatureMapperTest {

    @Test
    void toDTO_correctlyMapsEntityToDTO() {
        Temperature temp = new Temperature();
        temp.setId(5);
        Instant now = Instant.now();
        temp.setTimeStamp(now);
        temp.setTemperatureValue(20.5);

        TemperatureDTO dto = TemperatureMapper.toDTO(temp);

        assertEquals(5, dto.getId());
        assertEquals(20.5, dto.getTemperature_value());
        assertEquals(now, dto.getTime_stamp());
    }

    @Test
    void toEntity_correctlyMapsDTOToEntity() {
        TemperatureDTO dto = new TemperatureDTO();
        dto.setId(10);
        Instant now = Instant.now();
        dto.setTime_stamp(now);
        dto.setTemperature_value(19.3);

        Temperature entity = TemperatureMapper.toEntity(dto);

        assertEquals(10, entity.getId());
        assertEquals(19.3, entity.getTemperatureValue());
        assertEquals(now, entity.getTimeStamp());
    }
}

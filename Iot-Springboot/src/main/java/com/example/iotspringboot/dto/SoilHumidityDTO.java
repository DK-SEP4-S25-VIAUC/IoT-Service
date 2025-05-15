package com.example.iotspringboot.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.time.Instant;
import java.time.ZonedDateTime;

@JsonPropertyOrder(
    {
        "id", "time_stamp", "soil_humidity_value"
    }
)


public class SoilHumidityDTO {


  @JsonProperty("id")
  private int id;

  @JsonProperty("time_stamp")
  private Instant time_stamp;

  @JsonProperty("soil_humidity_value")
  private double soil_humidity_value;

  public int getId()
  {
    return id;
  }

  public void setId(int id)
  {
    this.id = id;
  }

  public Instant getTime_stamp()
  {
    return time_stamp;
  }

  public void setTime_stamp(Instant time_stamp)
  {
    this.time_stamp = time_stamp;
  }

  public double getSoil_humidity_value()
  {
    return soil_humidity_value;
  }

  public void setSoil_humidity_value(double soil_humidity_value)
  {
    this.soil_humidity_value = soil_humidity_value;
  }
}

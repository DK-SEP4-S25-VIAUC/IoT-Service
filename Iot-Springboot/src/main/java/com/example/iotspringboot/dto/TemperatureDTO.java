package com.example.iotspringboot.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.time.Instant;

@JsonPropertyOrder({
    "id", "time_stamp", "temperature"
})

public class TemperatureDTO
{
  @JsonProperty("id") private int id;
  @JsonProperty("time_stamp") private Instant time_stamp;
  @JsonProperty("temperature_value") private double temperature_value;


  @JsonProperty("id")
  public int getId()
  {
    return id;
  }

  @JsonProperty("id")
  public void setId(int id)
  {
    this.id = id;
  }

  @JsonProperty("time_stamp")
  public Instant getTime_stamp()
  {
    return time_stamp;
  }

  @JsonProperty("time_stamp")
  public void setTime_stamp(Instant time_stamp)
  {
    this.time_stamp = time_stamp;
  }

  @JsonProperty("temperature_value")
  public double getTemperature_value()
  {
    return temperature_value;
  }

  @JsonProperty("temperature_value")
  public void setTemperature_value(double temperature_value)
  {
    this.temperature_value = temperature_value;
  }
}

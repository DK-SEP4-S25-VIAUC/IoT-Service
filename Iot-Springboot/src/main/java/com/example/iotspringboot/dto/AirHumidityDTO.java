package com.example.iotspringboot.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.time.Instant;
@JsonPropertyOrder(
    {
        "id", "time_stamp", "air_humidity_value"
    }
    )

public class AirHumidityDTO
{
  @JsonProperty("id")
  private int id;
  @JsonProperty("time_stamp")
  private Instant time_stamp;
  @JsonProperty("air_humidity_value")
  private double air_humidity_value;

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

  public double getAirHumidityValue()
  {
    return air_humidity_value;
  }

  public void setAirHumidityValue(double airHumidityValue)
  {
    this.air_humidity_value = airHumidityValue;
  }
}

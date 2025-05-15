package com.example.iotspringboot.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.time.Instant;

@JsonPropertyOrder(
    {
        "id", "time_stamp", "light_value"
    }
)

public class LightDTO
{
  @JsonProperty("id")
  private int id;
  @JsonProperty("time_stamp")
  private Instant time_stamp;
  @JsonProperty("light_value")
  private double light_value;

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

  public double getLight_value()
  {
    return light_value;
  }

  public void setLight_value(double light_value)
  {
    this.light_value = light_value;
  }
}

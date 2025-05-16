package com.example.iotspringboot.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.time.Instant;

@JsonPropertyOrder({
    "id", "time_stamp", "water_level", "watered_amount"
})

public class WaterDTO
{
  @JsonProperty("id")
  private int id;
  @JsonProperty("time_stamp")
  private Instant time_stamp;
  @JsonProperty("water_level")
  private Double water_level;
  @JsonProperty("watered_amount")
  private Double watered_amount;

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
  public Instant getTimestamp()
  {
    return time_stamp;
  }

  @JsonProperty("time_stamp")
  public void setTimestamp(Instant timestamp)
  {
    this.time_stamp = timestamp;
  }

  @JsonProperty("water_level")
  public Double getWater_level()
  {
    return water_level;
  }

  @JsonProperty("water_level")
  public void setWater_level(Double water_level)
  {
    this.water_level = water_level;
  }

  @JsonProperty("watered_amount")
  public Double getWatered_amount()
  {
    return watered_amount;
  }

  @JsonProperty("watered_amount")
  public void setWatered_amount(Double watered_amount)
  {
    this.watered_amount = watered_amount;
  }
}

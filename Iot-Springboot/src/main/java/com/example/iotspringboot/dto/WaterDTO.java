package com.example.iotspringboot.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.Instant;

public class WaterDTO
{
  private int id;
  private Instant time_stamp;
  private Double water_level;
  private Double watered_amount;

  public int getId()
  {
    return id;
  }

  public void setId(int id)
  {
    this.id = id;
  }

  public Instant getTimestamp()
  {
    return time_stamp;
  }

  public void setTimestamp(Instant timestamp)
  {
    this.time_stamp = timestamp;
  }

  public Double getWater_level()
  {
    return water_level;
  }

  public void setWater_level(Double water_level)
  {
    this.water_level = water_level;
  }

  public Double getWatered_amount()
  {
    return watered_amount;
  }

  public void setWatered_amount(Double watered_amount)
  {
    this.watered_amount = watered_amount;
  }
}

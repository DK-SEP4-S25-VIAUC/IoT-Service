package com.example.iotspringboot.dto;

import java.time.Instant;

public class WaterDTO
{
  private int id;
  private Instant time_stamp;
  private double water_level;
  private double watered_amount;

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

  public double getWater_level()
  {
    return water_level;
  }

  public void setWater_level(double water_level)
  {
    this.water_level = water_level;
  }

  public double getWatered_amount()
  {
    return watered_amount;
  }

  public void setWatered_amount(double watered_amount)
  {
    this.watered_amount = watered_amount;
  }
}

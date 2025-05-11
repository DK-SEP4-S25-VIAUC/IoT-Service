package com.example.iotspringboot.dto;

import java.time.Instant;

public class WaterDTO
{
  private int id;
  private Instant timestamp;
  private double waterLevel;
  private double wateredAmount;

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
    return timestamp;
  }

  public void setTimestamp(Instant timestamp)
  {
    this.timestamp = timestamp;
  }

  public double getWaterLevel()
  {
    return waterLevel;
  }

  public void setWaterLevel(double waterLevel)
  {
    this.waterLevel = waterLevel;
  }

  public double getWateredAmount()
  {
    return wateredAmount;
  }

  public void setWateredAmount(double wateredAmount)
  {
    this.wateredAmount = wateredAmount;
  }
}

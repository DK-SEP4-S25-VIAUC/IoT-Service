package com.example.iotspringboot.dto;

import java.time.Instant;

public class LightDTO
{
  private int id;
  private Instant timestamp;
  private double lightValue;

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

  public double getLightValue()
  {
    return lightValue;
  }

  public void setLightValue(double lightValue)
  {
    this.lightValue = lightValue;
  }
}

package com.example.iotspringboot.dto;

import java.time.Instant;

public class AirHumidityDTO
{
  private int id;
  private Instant timestamp;
  private double airHumidityValue;

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

  public double getAirHumidityValue()
  {
    return airHumidityValue;
  }

  public void setAirHumidityValue(double airHumidityValue)
  {
    this.airHumidityValue = airHumidityValue;
  }
}

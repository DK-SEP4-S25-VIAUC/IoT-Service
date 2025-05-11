package com.example.iotspringboot.dto;

import java.time.Instant;

public class SampleDTO
{
  private int id;
  private double soilHumidity;
  private double airHumidity;
  private double airTemperature;
  private double lightValue;
  private Instant timestamp;

  public int getId()
  {
    return id;
  }

  public void setId(int id)
  {
    this.id = id;
  }

  public double getSoilHumidity()
  {
    return soilHumidity;
  }

  public void setSoilHumidity(double soilHumidity)
  {
    this.soilHumidity = soilHumidity;
  }

  public double getAirHumidity()
  {
    return airHumidity;
  }

  public void setAirHumidity(double airHumidity)
  {
    this.airHumidity = airHumidity;
  }

  public double getAirTemperature()
  {
    return airTemperature;
  }

  public void setAirTemperature(double airTemperature)
  {
    this.airTemperature = airTemperature;
  }

  public double getLightValue()
  {
    return lightValue;
  }

  public void setLightValue(double lightValue)
  {
    this.lightValue = lightValue;
  }

  public Instant getTimestamp()
  {
    return timestamp;
  }

  public void setTimestamp(Instant timestamp)
  {
    this.timestamp = timestamp;
  }
}

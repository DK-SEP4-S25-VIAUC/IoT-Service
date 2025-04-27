package com.example.iotspringboot.dto;

import java.time.Instant;

public class TemperatureDTO
{
  private int id;
  private Instant time_stamp;
  private double temperature_value;

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

  public double getTemperature_value()
  {
    return temperature_value;
  }

  public void setTemperature_value(double temperature_value)
  {
    this.temperature_value = temperature_value;
  }
}

package com.example.iotspringboot.dto;

import java.time.Instant;

public class SoilHumidityDTO
{
  private int id;
  private Instant time_stamp;
  private double soil_humidity_value;

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

  public double getSoil_humidity_value()
  {
    return soil_humidity_value;
  }

  public void setSoil_humidity_value(double soil_humidity_value)
  {
    this.soil_humidity_value = soil_humidity_value;
  }
}

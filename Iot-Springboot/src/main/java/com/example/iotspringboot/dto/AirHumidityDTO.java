package com.example.iotspringboot.dto;

import java.time.Instant;

public class AirHumidityDTO
{
  private int id;
  private Instant time_stamp;
  private double air_humidity_value;

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

  public double getAirHumidityValue()
  {
    return air_humidity_value;
  }

  public void setAirHumidityValue(double airHumidityValue)
  {
    this.air_humidity_value = airHumidityValue;
  }
}

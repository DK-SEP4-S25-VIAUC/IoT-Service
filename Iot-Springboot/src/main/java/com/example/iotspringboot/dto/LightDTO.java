package com.example.iotspringboot.dto;

import java.time.Instant;

public class LightDTO
{
  private int id;
  private Instant time_stamp;
  private double light_value;

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

  public double getLight_value()
  {
    return light_value;
  }

  public void setLight_value(double light_value)
  {
    this.light_value = light_value;
  }
}

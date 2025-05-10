package com.example.iotspringboot.dto;

import java.time.Instant;

public class SampleDTO
{
  private int id;
  private Double soil_humidity;
  private Double air_humidity;
  private Double air_temperature;
  private Double light_value;
  private Instant time_stamp;

  public int getId()
  {
    return id;
  }

  public void setId(int id)
  {
    this.id = id;
  }

  public Double getSoil_humidity()
  {
    return soil_humidity;
  }

  public void setSoil_humidity(Double soil_humidity)
  {
    this.soil_humidity = soil_humidity;
  }

  public Double getAir_humidity()
  {
    return air_humidity;
  }

  public void setAir_humidity(Double air_humidity)
  {
    this.air_humidity = air_humidity;
  }

  public Double getAir_temperature()
  {
    return air_temperature;
  }

  public void setAir_temperature(Double air_temperature)
  {
    this.air_temperature = air_temperature;
  }

  public Double getLight_value()
  {
    return light_value;
  }

  public void setLight_value(Double light_value)
  {
    this.light_value = light_value;
  }

  public Instant getTime_stamp()
  {
    return time_stamp;
  }

  public void setTime_stamp(Instant time_stamp)
  {
    this.time_stamp = time_stamp;
  }
}

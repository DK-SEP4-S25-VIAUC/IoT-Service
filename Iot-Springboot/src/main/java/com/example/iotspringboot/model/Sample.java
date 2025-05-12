package com.example.iotspringboot.model;

import jakarta.persistence.*;

import java.time.Instant;

@Entity public class Sample
{
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY) private int id;

  @Column(name = "soil_humidity", nullable = true)
  private Double soilHumidity;
  @Column(name = "air_humidity", nullable = true)
  private Double airHumidity;
  @Column(name = "air_temperature", nullable = true)
  private Double airTemperature;
  @Column(name = "light_value", nullable = true)
  private Double lightValue;
  @Column(name = "time_stamp")
  private Instant timeStamp;
  @Column(name = "lower_threshold", nullable = true)
  private Double lower_threshold;

  public Sample() {}

  public Sample(double soilHumidity, double airHumidity, double airTemperature, double lightValue, Instant timeStamp, double lower_threshold) {
    this.soilHumidity = soilHumidity;
    this.airHumidity = airHumidity;
    this.airTemperature = airTemperature;
    this.lightValue = lightValue;
    this.timeStamp = timeStamp;
    this.lower_threshold = lower_threshold;
  }


  public int getId()
  {
    return id;
  }

  public void setId(int id)
  {
    this.id = id;
  }

  public Double getSoilHumidity()
  {
    return soilHumidity;
  }

  public void setSoilHumidity(Double soilHumidity)
  {
    this.soilHumidity = soilHumidity;
  }

  public Double getAirHumidity()
  {
    return airHumidity;
  }

  public void setAirHumidity(Double airHumidity)
  {
    this.airHumidity = airHumidity;
  }

  public Double getAirTemperature()
  {
    return airTemperature;
  }

  public void setAirTemperature(Double airTemperature)
  {
    this.airTemperature = airTemperature;
  }

  public Double getLightValue()
  {
    return lightValue;
  }

  public void setLightValue(Double lightValue)
  {
    this.lightValue = lightValue;
  }

  public Double getLower_threshold()
  {
    return lower_threshold;
  }

  public void setLower_threshold(Double lower_threshold)
  {
    this.lower_threshold = lower_threshold;
  }

  public Instant getTimeStamp()
  {
    return timeStamp;
  }

  public void setTimeStamp(Instant timeStamp)
  {
    this.timeStamp = timeStamp;
  }
}

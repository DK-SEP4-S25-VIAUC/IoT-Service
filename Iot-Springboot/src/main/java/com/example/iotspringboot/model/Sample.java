package com.example.iotspringboot.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.time.Instant;

@Entity public class Sample
{
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY) private int id;

  private Double soilHumidity;
  private Double airHumidity;
  private Double airTemperature;
  private Double lightValue;
  private Instant timeStamp;

  public Sample() {}

  public Sample(double soilHumidity, double airHumidity, double airTemperature, double lightValue, Instant timeStamp) {
    this.soilHumidity = soilHumidity;
    this.airHumidity = airHumidity;
    this.airTemperature = airTemperature;
    this.lightValue = lightValue;
    this.timeStamp = timeStamp;
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

  public Instant getTimeStamp()
  {
    return timeStamp;
  }

  public void setTimeStamp(Instant timeStamp)
  {
    this.timeStamp = timeStamp;
  }
}

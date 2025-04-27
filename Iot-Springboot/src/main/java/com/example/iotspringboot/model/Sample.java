package com.example.iotspringboot.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.time.Instant;

@Entity public class Sample
{
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY) private int id;

  private double soilHumidity;
  private double airHumidity;
  private double airTemperature;
  private double lightValue;
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

  public Instant getTimeStamp()
  {
    return timeStamp;
  }

  public void setTimeStamp(Instant timeStamp)
  {
    this.timeStamp = timeStamp;
  }
}

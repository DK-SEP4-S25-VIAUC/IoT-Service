package com.example.iotspringboot.model;

import jakarta.persistence.*;

import java.time.Instant;
import java.time.ZonedDateTime;

@Entity
public class SoilHumidity
{
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  private double soilHumidityValue;

  @Column(nullable = false)
  private Instant timeStamp;

  public int getId()
  {
    return id;
  }

  public void setId(int id)
  {
    this.id = id;
  }

  public double getSoilHumidityValue()
  {
    return soilHumidityValue;
  }

  public void setSoilHumidityValue(double soilHumidityValue)
  {
    this.soilHumidityValue = soilHumidityValue;
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

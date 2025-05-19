package com.example.iotspringboot.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

import java.time.Instant;
import java.time.LocalDateTime;

@Entity
public class Temperature
{
  @Id
  @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
  private int id;

  @Column(name = "time_stamp")
  private Instant timeStamp;

  @Column(name = "temperature_value")
  private double temperatureValue;

  public int getId()
  {
    return id;
  }

  public void setId(int id)
  {
    this.id = id;
  }

  public Instant getTimeStamp()
  {
    return timeStamp;
  }

  public void setTimeStamp(Instant timeStamp)
  {
    this.timeStamp = timeStamp;
  }

  public double getTemperatureValue()
  {
    return temperatureValue;
  }

  public void setTemperatureValue(double temperatureValue)
  {
    this.temperatureValue = temperatureValue;
  }
}

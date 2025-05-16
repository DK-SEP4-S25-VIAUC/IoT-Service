package com.example.iotspringboot.model;

import jakarta.persistence.*;

import java.time.Instant;

@Entity
public class AirHumidity
{
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  @Column(name = "air_humidity_value")
  private double airHumidityValue;

  @Column(name = "time_stamp")
  private Instant timeStamp;

  public int getId()
  {
    return id;
  }

  public void setId(int id)
  {
    this.id = id;
  }

  public double getAirHumidityValue()
  {
    return airHumidityValue;
  }

  public void setAirHumidityValue(double airHumidityValue)
  {
    this.airHumidityValue = airHumidityValue;
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

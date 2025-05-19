package com.example.iotspringboot.model;

import jakarta.persistence.*;

import java.time.Instant;

@Entity
public class Light
{
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  @Column(name = "light_value")
  private double lightValue;

  @Column(name = "time_stamp")
  private Instant timeStamp;

  public double getLightValue()
  {
    return lightValue;
  }

  public void setLightValue(double lightValue)
  {
    this.lightValue = lightValue;
  }

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
}

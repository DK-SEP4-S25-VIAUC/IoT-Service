package com.example.iotspringboot.model;

import jakarta.persistence.*;

import java.time.Instant;

@Entity
public class Water
{

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  @Column(name = "water_level", nullable = true)
  private Double waterLevel;
  @Column(name = "watered_amount")
  private Double wateredAmount;
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

  public Double getWaterLevel()
  {
    return waterLevel;
  }

  public void setWaterLevel(Double waterLevel)
  {
    this.waterLevel = waterLevel;
  }

  public Double getWateredAmount()
  {
    return wateredAmount;
  }

  public void setWateredAmount(double wateredAmount)
  {
    this.wateredAmount = wateredAmount;
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

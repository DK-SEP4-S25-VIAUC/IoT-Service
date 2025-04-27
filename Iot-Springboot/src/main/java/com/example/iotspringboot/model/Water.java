package com.example.iotspringboot.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.time.Instant;

@Entity
public class Water
{

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  private double waterLevel;
  private double wateredAmount;
  private Instant timeStamp;

  public int getId()
  {
    return id;
  }

  public void setId(int id)
  {
    this.id = id;
  }

  public double getWaterLevel()
  {
    return waterLevel;
  }

  public void setWaterLevel(double waterLevel)
  {
    this.waterLevel = waterLevel;
  }

  public double getWateredAmount()
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

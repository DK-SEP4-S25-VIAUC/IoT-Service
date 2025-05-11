package com.example.iotspringboot.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.time.Instant;

@Entity
public class Watering
{
  @Id
  @GeneratedValue(strategy =  GenerationType.IDENTITY)
  private int id;

  private double waterAmount;

  private Instant timeStamp;

  public int getId()
  {
    return id;
  }

  public void setId(int id)
  {
    this.id = id;
  }

  public double getWaterAmount()
  {
    return waterAmount;
  }

  public void setWaterAmount(double waterAmount)
  {
    this.waterAmount = waterAmount;
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

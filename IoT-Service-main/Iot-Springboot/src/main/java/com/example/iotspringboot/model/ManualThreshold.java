package com.example.iotspringboot.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class ManualThreshold
{
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  private double lowerbound;
  private double upperbound;

  public int getId()
  {
    return id;
  }

  public void setId(int id)
  {
    this.id = id;
  }

  public double getLowerbound()
  {
    return lowerbound;
  }

  public void setLowerbound(double lowerbound)
  {
    this.lowerbound = lowerbound;
  }

  public double getUpperbound()
  {
    return upperbound;
  }

  public void setUpperbound(double upperbound)
  {
    this.upperbound = upperbound;
  }
}

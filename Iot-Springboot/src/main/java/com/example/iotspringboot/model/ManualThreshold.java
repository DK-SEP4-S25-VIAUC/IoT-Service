package com.example.iotspringboot.model;

import jakarta.persistence.*;

@Entity
public class ManualThreshold
{
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  @Column(name = "lowerbound")
  private double lowerbound;
  @Column(name = "upperbound")
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

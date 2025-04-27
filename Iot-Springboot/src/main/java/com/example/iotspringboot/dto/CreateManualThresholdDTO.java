package com.example.iotspringboot.dto;

public class CreateManualThresholdDTO
{
  private double lowerbound;
  private double upperbound;

  public double getUpperbound()
  {
    return upperbound;
  }

  public void setUpperbound(double upperbound)
  {
    this.upperbound = upperbound;
  }

  public double getLowerbound()
  {
    return lowerbound;
  }

  public void setLowerbound(double lowerbound)
  {
    this.lowerbound = lowerbound;
  }
}

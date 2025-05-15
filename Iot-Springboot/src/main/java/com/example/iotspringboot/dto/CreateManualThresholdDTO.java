package com.example.iotspringboot.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder(
  {
    "lowerbound", "upperbound"
  }
)

public class CreateManualThresholdDTO
{
  @JsonProperty("lowerbound")
  private double lowerbound;
  @JsonProperty("upperbound")
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

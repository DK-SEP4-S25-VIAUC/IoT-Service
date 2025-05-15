package com.example.iotspringboot.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CreateSoilHumidityDTO {

  @JsonProperty("soil_humidity_value")
  private double soil_humidity_value;

  public double getSoil_humidity_value() {
    return soil_humidity_value;
  }

  public void setSoil_humidity_value(double soil_humidity_value) {
    this.soil_humidity_value = soil_humidity_value;
  }
}
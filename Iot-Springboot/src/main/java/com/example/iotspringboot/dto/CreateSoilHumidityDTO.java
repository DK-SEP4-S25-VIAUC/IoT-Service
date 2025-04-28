package com.example.iotspringboot.dto;

public class CreateSoilHumidityDTO {
  private double soil_humidity_value;

  public double getSoil_humidity_value() {
    return soil_humidity_value;
  }

  public void setSoil_humidity_value(double soil_humidity_value) {
    this.soil_humidity_value = soil_humidity_value;
  }
}
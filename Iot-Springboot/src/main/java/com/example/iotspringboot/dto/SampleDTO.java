package com.example.iotspringboot.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.Instant;

public class SampleDTO {
  private int id;

  @JsonProperty("soil_humidity")
  private Double soilHumidity;

  @JsonProperty("air_humidity")
  private Double airHumidity;

  @JsonProperty("air_temperature")
  private Double airTemperature;

  @JsonProperty("light_value")
  private Double lightValue;

  private Instant timestamp;

  private Double lower_threshold;

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public Double getSoilHumidity() {
    return soilHumidity;
  }

  public void setSoilHumidity(Double soilHumidity) {
    this.soilHumidity = soilHumidity;
  }

  public Double getAirHumidity() {
    return airHumidity;
  }

  public void setAirHumidity(Double airHumidity) {
    this.airHumidity = airHumidity;
  }

  public Double getAirTemperature() {
    return airTemperature;
  }

  public void setAirTemperature(Double airTemperature) {
    this.airTemperature = airTemperature;
  }

  public Double getLightValue() {
    return lightValue;
  }

  public void setLightValue(Double lightValue) {
    this.lightValue = lightValue;
  }

  public Instant getTimestamp() {
    return timestamp;
  }

  public void setTimestamp(Instant timestamp) {
    this.timestamp = timestamp;
  }

  public Double getLower_threshold()
  {
    return lower_threshold;
  }

  public void setLower_threshold(Double lower_threshold)
  {
    this.lower_threshold = lower_threshold;
  }
}

package com.example.iotspringboot.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.time.Instant;
@JsonPropertyOrder(
    {
        "id", "soil_humidity", "air_humidity", "air_temperature", "light_value", "timestamp", "lower_threshold"
    }
)
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

  @JsonProperty("soil_humidity")
  public Double getSoilHumidity() {
    return soilHumidity;
  }

  @JsonProperty("soil_humidity")
  public void setSoilHumidity(Double soilHumidity) {
    this.soilHumidity = soilHumidity;
  }

  @JsonProperty("air_humidity")
  public Double getAirHumidity() {
    return airHumidity;
  }

  @JsonProperty("air_humidity")
  public void setAirHumidity(Double airHumidity) {
    this.airHumidity = airHumidity;
  }

  @JsonProperty("air_temperature")
  public Double getAirTemperature() {
    return airTemperature;
  }

  @JsonProperty("air_temperature")
  public void setAirTemperature(Double airTemperature) {
    this.airTemperature = airTemperature;
  }

  @JsonProperty("light_value")
  public Double getLightValue() {
    return lightValue;
  }

  @JsonProperty("light_value")
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

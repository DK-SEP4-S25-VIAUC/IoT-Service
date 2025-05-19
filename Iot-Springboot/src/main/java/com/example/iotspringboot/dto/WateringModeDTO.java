package com.example.iotspringboot.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class WateringModeDTO {

  @JsonProperty("automatic_watering")
  private boolean automaticWatering;

  @JsonProperty("automatic_watering")
  public boolean isAutomaticWatering() {
    return automaticWatering;
  }

  @JsonProperty("automatic_watering")
  public void setAutomaticWatering(boolean automaticWatering) {
    this.automaticWatering = automaticWatering;
  }
}

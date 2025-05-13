package com.example.iotspringboot.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class WateringModeDTO {

  @JsonProperty("automatic_watering")
  private boolean automaticWatering;

  public boolean isAutomaticWatering() {
    return automaticWatering;
  }

  public void setAutomaticWatering(boolean automaticWatering) {
    this.automaticWatering = automaticWatering;
  }
}

package com.example.iotspringboot.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CreateManualThresholdDTORequest {
  @JsonProperty("CreateManualThresholdDTO")
  private CreateManualThresholdDTO dto;

  public CreateManualThresholdDTO getDto() {
    return dto;
  }

  public void setDto(CreateManualThresholdDTO dto) {
    this.dto = dto;
  }
}

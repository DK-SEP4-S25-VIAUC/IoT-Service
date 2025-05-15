package com.example.iotspringboot.model;

import jakarta.persistence.*;

@Entity
public class WateringMode {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "automatic_watering")
  private boolean automaticWatering;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public boolean isAutomaticWatering() {
    return automaticWatering;
  }

  public void setAutomaticWatering(boolean automaticWatering) {
    this.automaticWatering = automaticWatering;
  }
}

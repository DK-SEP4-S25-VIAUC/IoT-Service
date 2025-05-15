package com.example.iotspringboot.model;

import jakarta.persistence.*;

@Entity
public class WateringMode {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  private boolean automaticWatering;

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public boolean isAutomaticWatering() {
    return automaticWatering;
  }

  public void setAutomaticWatering(boolean automaticWatering) {
    this.automaticWatering = automaticWatering;
  }
}

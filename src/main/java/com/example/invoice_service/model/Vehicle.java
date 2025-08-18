package com.example.invoice_service.model;

import java.math.BigDecimal;

public class Vehicle {
  private String id;
  private String model;
  private String vin;
  private String color;
  private int year;
  private BigDecimal basePrice;

  public Vehicle() {}

  public Vehicle(String id, String model, String vin, String color, int year, BigDecimal basePrice) {
    this.id = id;
    this.model = model;
    this.vin = vin;
    this.color = color;
    this.year = year;
    this.basePrice = basePrice;
  }

  // getters & setters
  public String getId() { return id; }
  public void setId(String id) { this.id = id; }
  public String getModel() { return model; }
  public void setModel(String model) { this.model = model; }
  public String getVin() { return vin; }
  public void setVin(String vin) { this.vin = vin; }
  public String getColor() { return color; }
  public void setColor(String color) { this.color = color; }
  public int getYear() { return year; }
  public void setYear(int year) { this.year = year; }
  public BigDecimal getBasePrice() { return basePrice; }
  public void setBasePrice(BigDecimal basePrice) { this.basePrice = basePrice; }
}


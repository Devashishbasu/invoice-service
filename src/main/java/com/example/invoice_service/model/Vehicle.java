package com.example.invoice_service.model;

import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class Vehicle {
  private String id;
  private String make;
  private String model;
  private String year;
  private BigDecimal basePrice;


}


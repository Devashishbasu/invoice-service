package com.example.invoice_service.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@Data
@NoArgsConstructor
@AllArgsConstructor
public class Dealer {
  private String id;
  private String name;
  private String address;
  private String phone;

}

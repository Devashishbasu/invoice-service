package com.example.invoice_service.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class Dealer {
  private String id;
  private String name;
  private String address;
  private String phone;

}

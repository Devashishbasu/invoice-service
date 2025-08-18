package com.example.invoice_service.model;


public class Dealer {
  private String id;
  private String name;
  private String address;
  private String taxId;
  private String phone;
  private String email;

  public Dealer() {}

  public Dealer(String id, String name, String address, String taxId, String phone, String email) {
    this.id = id;
    this.name = name;
    this.address = address;
    this.taxId = taxId;
    this.phone = phone;
    this.email = email;
  }

  // getters & setters
  public String getId() { return id; }
  public void setId(String id) { this.id = id; }
  public String getName() { return name; }
  public void setName(String name) { this.name = name; }
  public String getAddress() { return address; }
  public void setAddress(String address) { this.address = address; }
  public String getTaxId() { return taxId; }
  public void setTaxId(String taxId) { this.taxId = taxId; }
  public String getPhone() { return phone; }
  public void setPhone(String phone) { this.phone = phone; }
  public String getEmail() { return email; }
  public void setEmail(String email) { this.email = email; }
}

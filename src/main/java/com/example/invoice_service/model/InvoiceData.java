package com.example.invoice_service.model;


import java.math.BigDecimal;
import java.time.OffsetDateTime;

public class InvoiceData {
  private String invoiceNo;
  private OffsetDateTime timestamp;
  private String transactionId;
  private Dealer dealer;
  private Vehicle vehicle;
  private String customerName;
  private BigDecimal tax;
  private BigDecimal basePrice;
  private BigDecimal total;
  private String qrBase64;

  public InvoiceData() {}

  // getters & setters
  public String getInvoiceNo() { return invoiceNo; }
  public void setInvoiceNo(String invoiceNo) { this.invoiceNo = invoiceNo; }
  public OffsetDateTime getTimestamp() { return timestamp; }
  public void setTimestamp(OffsetDateTime timestamp) { this.timestamp = timestamp; }
  public String getTransactionId() { return transactionId; }
  public void setTransactionId(String transactionId) { this.transactionId = transactionId; }
  public Dealer getDealer() { return dealer; }
  public void setDealer(Dealer dealer) { this.dealer = dealer; }
  public Vehicle getVehicle() { return vehicle; }
  public void setVehicle(Vehicle vehicle) { this.vehicle = vehicle; }
  public String getCustomerName() { return customerName; }
  public void setCustomerName(String customerName) { this.customerName = customerName; }
  public BigDecimal getTax() { return tax; }
  public void setTax(BigDecimal tax) { this.tax = tax; }
  public BigDecimal getBasePrice() { return basePrice; }
  public void setBasePrice(BigDecimal basePrice) { this.basePrice = basePrice; }
  public BigDecimal getTotal() { return total; }
  public void setTotal(BigDecimal total) { this.total = total; }
  public String getQrBase64() { return qrBase64; }
  public void setQrBase64(String qrBase64) { this.qrBase64 = qrBase64; }
}

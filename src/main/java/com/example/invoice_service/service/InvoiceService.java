package com.example.invoice_service.service;

import com.example.invoice_service.model.Dealer;
import com.example.invoice_service.model.Vehicle;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
public class InvoiceService {
  private final DataSeeder dataSeeder;
  private final QrService qrService;
  private final TemplateService templateService;
  private final PdfService pdfService;
  private final ObjectMapper objectMapper = new ObjectMapper();

  public InvoiceService(DataSeeder dataSeeder, QrService qrService, TemplateService templateService, PdfService pdfService) {
    this.dataSeeder = dataSeeder;
    this.qrService = qrService;
    this.templateService = templateService;
    this.pdfService = pdfService;
  }

  private String generateInvoiceNo(String dealerId) {
    String date = LocalDate.now(ZoneId.of("Asia/Kolkata")).format(DateTimeFormatter.BASIC_ISO_DATE);
    String shortId = UUID.randomUUID().toString().substring(0, 8).toUpperCase();
    return "INV-" + date + "-" + dealerId + "-" + shortId;
  }

  public byte[] createInvoicePdf(String dealerId, String vehicleId, String customerName) throws Exception {
    Dealer dealer = dataSeeder.findDealer(dealerId)
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "dealer not found"));
    Vehicle vehicle = dataSeeder.findVehicle(vehicleId)
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "vehicle not found"));

    BigDecimal base = vehicle.getBasePrice();
    BigDecimal tax = base.multiply(new BigDecimal("0.10")).setScale(2, RoundingMode.HALF_UP);
    BigDecimal total = base.add(tax).setScale(2, RoundingMode.HALF_UP);

    String invoiceNo = generateInvoiceNo(dealerId);
    OffsetDateTime ts = OffsetDateTime.now(ZoneId.of("Asia/Kolkata"));
    String txId = UUID.randomUUID().toString();

    Map<String, Object> vars = new HashMap<>();
    vars.put("dealer", dealer);
    vars.put("vehicle", vehicle);
    vars.put("customerName", customerName);
    vars.put("invoiceNo", invoiceNo);
    vars.put("timestamp", ts.format(DateTimeFormatter.ISO_OFFSET_DATE_TIME));
    vars.put("base", base);
    vars.put("tax", tax);
    vars.put("total", total);
    vars.put("transactionId", txId);

    Map<String, Object> qrPayload = Map.of(
        "transactionId", txId,
        "dealerId", dealerId,
        "vehicleId", vehicleId,
        "amount", total.toPlainString(),
        "timestamp", ts.toString()
    );
    String qrJson = objectMapper.writeValueAsString(qrPayload);
    String qrBase64 = qrService.generateQrBase64(qrJson, 200);
    vars.put("qrBase64", qrBase64);

    String html = templateService.render("invoice", vars);
    return pdfService.htmlToPdf(html);
  }
}

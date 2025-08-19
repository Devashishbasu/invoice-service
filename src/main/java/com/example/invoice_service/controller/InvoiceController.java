package com.example.invoice_service.controller;

import com.example.invoice_service.service.InvoiceService;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api")
public class InvoiceController {

  private final InvoiceService invoiceService;

  public InvoiceController(InvoiceService invoiceService) {
    this.invoiceService = invoiceService;
  }


  @GetMapping("/invoices")
  public ResponseEntity<byte[]> getInvoice(
      @RequestParam String dealerId,
      @RequestParam String vehicleId,
      @RequestParam String customerName
  ) throws Exception {

    if (customerName == null || customerName.trim().isEmpty()) {
      throw new ResponseStatusException(org.springframework.http.HttpStatus.BAD_REQUEST, "customerName required");
    }

    byte[] pdf = invoiceService.createInvoicePdf(dealerId, vehicleId, customerName);
    String filename = "invoice-" + dealerId + "-" + java.util.UUID.randomUUID().toString().substring(0, 8) + ".pdf";

    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_PDF);
    headers.setContentDisposition(ContentDisposition.attachment().filename(filename).build());

    return ResponseEntity.ok().headers(headers).body(pdf);
  }
}

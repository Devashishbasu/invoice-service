package com.example.invoice_service.service;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.util.Base64;

@Service
public class QrService {

  public String generateQrBase64(String payloadJson, int size) throws Exception {
    QRCodeWriter writer = new QRCodeWriter();
    BitMatrix matrix = writer.encode(payloadJson, BarcodeFormat.QR_CODE, size, size);
    try (ByteArrayOutputStream os = new ByteArrayOutputStream()) {
      MatrixToImageWriter.writeToStream(matrix, "PNG", os);
      return Base64.getEncoder().encodeToString(os.toByteArray());
    }
  }
}

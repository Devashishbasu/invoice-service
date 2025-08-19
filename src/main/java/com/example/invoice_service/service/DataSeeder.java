package com.example.invoice_service.service;

import com.example.invoice_service.model.Dealer;
import com.example.invoice_service.model.Vehicle;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Component
public class DataSeeder {

  private final Map<String, Dealer> dealers = new HashMap<>();
  private final Map<String, Vehicle> vehicles = new HashMap<>();

  public DataSeeder() {
    // Seed dealers
    dealers.put("1", new Dealer("1", "Shree Auto Dealers", "MG Road, New Delhi, MH", "+91-9876543210"));
    dealers.put("2", new Dealer("2", "Omkar Motors", "Park Street, Kolkata, WB", "+91-9123456789"));

    // Seed vehicles
    vehicles.put("1", new Vehicle("1", "Tata", "Nexon EV", "2024", new BigDecimal("1499000")));
    vehicles.put("2", new Vehicle("2", "Hyundai", "Creta", "2025", new BigDecimal("1490000")));
  }

  public Optional<Dealer> findDealer(String id) {
    return Optional.ofNullable(dealers.get(id));
  }

  public Optional<Vehicle> findVehicle(String id) {
    return Optional.ofNullable(vehicles.get(id));
  }
}

package com.antonio;

import java.sql.SQLException;
import java.util.List;

import com.antonio.service.InvoiceService;
import com.antonio.model.InvoiceTotal;

public class App {
  public static void main(String[] args) {
    InvoiceService service = new InvoiceService();
    try {
      List<InvoiceTotal> totals = service.findInvoiceTotal();
      for (InvoiceTotal invoice : totals) {
        System.out.printf("%d | %s | %.2f%n",
            invoice.getId(),
            invoice.getCustomerName(),
            invoice.getTotal());
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }
}

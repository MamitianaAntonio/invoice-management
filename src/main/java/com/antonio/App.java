package com.antonio;

import java.sql.SQLException;
import java.util.List;

import com.antonio.model.InvoiceStatusTotals;
import com.antonio.service.InvoiceService;
import com.antonio.model.InvoiceTotal;

public class App {
  public static void main(String[] args) {
    InvoiceService service = new InvoiceService();
    try {
      // Q1
      List<InvoiceTotal> totals = service.findInvoiceTotal();
      for (InvoiceTotal invoice : totals) {
        System.out.printf("%d | %s | %.2f%n",
            invoice.getId(),
            invoice.getCustomerName(),
            invoice.getTotal());
      }

      // Q2
      totals = service.findConfirmedAndPaidInvoiceTotals();
      System.out.println("CONFIRMED and PAID invoice :");
      for (InvoiceTotal t : totals) {
        System.out.printf("%d | %s | %s | %.2f%n",
            t.getId(),
            t.getCustomerName(),
            t.getStatus(),
            t.getTotal());

      // Q3
      InvoiceStatusTotals statusTotals = service.computeStatusTotals();
      System.out.println("Q3 - Totaux cumulés par statut :");
      System.out.printf("PAID: %.2f | CONFIRMED: %.2f | DRAFT: %.2f%n",
          statusTotals.getTotalPaid(),
          statusTotals.getTotalConfirmed(),
          statusTotals.getTotalDraft());
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }
}

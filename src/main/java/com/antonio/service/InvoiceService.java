package com.antonio.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.antonio.config.DBConnection;
import com.antonio.model.InvoiceStatus;
import com.antonio.model.InvoiceTotal;

public class InvoiceService {
  public List<InvoiceTotal> findInvoiceTotal() throws SQLException {
    List<InvoiceTotal> invoiceTotals = new ArrayList<>();
    String sql = """
        SELECT invoice.id, invoice.customer_name,
          SUM(invoice_line.quantity * invoice_line.unit_price) AS total
          FROM invoice_line
          JOIN invoice ON invoice.id = invoice_line.invoice_id
          GROUP By invoice.id, invoice.customer_name
          ORDER BY invoice.id;
        """;

    try (Connection connection = DBConnection.getConnection();
        PreparedStatement ps = connection.prepareStatement(sql)) {
      ResultSet rs = ps.executeQuery();
      while (rs.next()) {
        InvoiceTotal invoiceTotal = new InvoiceTotal();
        invoiceTotal.setId(rs.getInt("id"));
        invoiceTotal.setCustomerName(rs.getString("customer_name"));
        invoiceTotal.setTotal(rs.getDouble("total"));
        invoiceTotals.add(invoiceTotal);
      }
    }

    return invoiceTotals;
  }


  public List<InvoiceTotal> findConfirmedAndPaidInvoiceTotals() throws SQLException {
    List<InvoiceTotal> invoiceTotals = new ArrayList<>();
    String sql = """
        SELECT i.id, i.customer_name, i.status,
               SUM(il.quantity * il.unit_price) AS total
        FROM invoice i
        JOIN invoice_line il ON i.id = il.invoice_id
        WHERE i.status IN ('CONFIRMED', 'PAID')
        GROUP BY i.id, i.customer_name, i.status
        ORDER BY i.id;
    """;

    try (Connection connection = DBConnection.getConnection();
         PreparedStatement ps = connection.prepareStatement(sql)) {
      ResultSet rs = ps.executeQuery();
      while (rs.next()) {
        InvoiceTotal invoiceTotal = new InvoiceTotal();
        invoiceTotal.setId(rs.getInt("id"));
        invoiceTotal.setCustomerName(rs.getString("customer_name"));
        invoiceTotal.setStatus(InvoiceStatus.valueOf(rs.getString("status")));
        invoiceTotal.setTotal(rs.getDouble("total"));
        invoiceTotals.add(invoiceTotal);
      }
    }

    return invoiceTotals;
  }
}

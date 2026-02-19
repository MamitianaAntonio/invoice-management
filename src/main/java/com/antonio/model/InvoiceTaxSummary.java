package com.antonio.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class InvoiceTaxSummary {
  private int invoiceId;
  private double totalHt;
  private double totalTva;
  private double totalTtc;
}

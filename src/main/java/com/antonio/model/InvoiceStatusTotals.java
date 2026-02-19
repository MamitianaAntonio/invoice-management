package com.antonio.model;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class InvoiceStatusTotals {
  private double totalPaid;
  private double totalConfirmed;
  private double totalDraft;
}

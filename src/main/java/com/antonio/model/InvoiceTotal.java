package com.antonio.model;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Setter
@EqualsAndHashCode
@ToString
public class InvoiceTotal {
  private int id;
  private String customerName;
  private double total;
  private InvoiceStatus status;
}

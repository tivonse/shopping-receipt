package com.github.tivon.shoppingreceipt.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class TaxRate {
    private BigDecimal salesTaxRate;
    private BigDecimal foodTaxRate;
    private BigDecimal clothingTaxRate;
}

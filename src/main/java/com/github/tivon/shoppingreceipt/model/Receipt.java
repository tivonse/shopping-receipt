package com.github.tivon.shoppingreceipt.model;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class Receipt {

    private List<Product> products;

    private BigDecimal subtotal;

    private BigDecimal tax;

    private BigDecimal total;

}

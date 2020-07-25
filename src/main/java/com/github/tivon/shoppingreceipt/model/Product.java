package com.github.tivon.shoppingreceipt.model;

import com.github.tivon.shoppingreceipt.model.constant.ProductCategory;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {

    private String item;

    private BigDecimal price;

    private int quantity;

    private ProductCategory category;

}

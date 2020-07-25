package com.github.tivon.shoppingreceipt.service;

import com.github.tivon.shoppingreceipt.model.Product;
import com.github.tivon.shoppingreceipt.mapper.TaxRateMapper;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class TaxService {

    // calculate the subtotal
    public BigDecimal calculateSubTotal(List<Product> products) {
        List<Product> checkedList = filterNullObject(products);
        return checkedList.stream()
                .map(p -> p.getPrice().multiply(BigDecimal.valueOf(p.getQuantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    // calculate the tax
    public BigDecimal calculateTax(List<Product> products, String location) {

        BigDecimal tax = null;
        List<Product> checkedList = filterNullObject(products);

        TaxRateMapper mapper = TaxRateMapper.getTaxRateMapperByLocation(location);

        tax = checkedList.stream().map(p -> {
                switch(p.getCategory()) {
                    case SALES:
                        return mapper.getTaxRate().getSalesTaxRate()
                                .multiply(p.getPrice().multiply(BigDecimal.valueOf(p.getQuantity())));
                    case FOOD:
                        return mapper.getTaxRate().getFoodTaxRate()
                                .multiply(p.getPrice().multiply(BigDecimal.valueOf(p.getQuantity())));
                    case CLOTHING:
                        return mapper.getTaxRate().getClothingTaxRate()
                                .multiply(p.getPrice().multiply(BigDecimal.valueOf(p.getQuantity())));
                    default:
                        return null;
                }
        }).reduce(BigDecimal.ZERO, BigDecimal::add);

        return roundUp(tax);
    }

    // calculate the after-tax total
    public BigDecimal calculateAfterTax(BigDecimal subtotal, BigDecimal tax) {
        if (subtotal != null && tax != null)
            return subtotal.add(tax);
        else
            throw new IllegalArgumentException("Both arguments should not be null");
    }

    // utility method
    private List<Product> filterNullObject(List<Product> products) {
        return products.stream()
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }

    // utility method
    private BigDecimal roundUp(BigDecimal input) {
        return input.divide(BigDecimal.valueOf(0.05), 0, RoundingMode.UP).multiply(BigDecimal.valueOf(0.05));
    }

}

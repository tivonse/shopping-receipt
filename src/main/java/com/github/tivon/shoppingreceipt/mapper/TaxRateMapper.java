package com.github.tivon.shoppingreceipt.mapper;

import com.github.tivon.shoppingreceipt.model.TaxRate;
import com.github.tivon.shoppingreceipt.model.constant.Location;

import java.math.BigDecimal;

public final class TaxRateMapper {

    private static TaxRateMapper TAX_RATE_MAP_CA;
    private static TaxRateMapper TAX_RATE_MAP_NY;
    private TaxRate taxRate;

    static {
        TAX_RATE_MAP_CA = new TaxRateMapper(
                new TaxRate(BigDecimal.valueOf(9.75).divide(BigDecimal.valueOf(100)), BigDecimal.ZERO, BigDecimal.ZERO));
        TAX_RATE_MAP_NY = new TaxRateMapper(
                new TaxRate(BigDecimal.valueOf(8.875).divide(BigDecimal.valueOf(100)), BigDecimal.ZERO, BigDecimal.ZERO));
    }

    TaxRateMapper(TaxRate taxRate) {
        this.taxRate = taxRate;
    }

    public static TaxRateMapper getTaxRateMapperByLocation(String location) {
        if (location == null)
            throw new IllegalArgumentException("Argument location should not be null");
        else if (Location.COUNTRY_CA.equals(location))
            return TAX_RATE_MAP_CA;
        else if (Location.COUNTRY_NY.equals(location))
            return TAX_RATE_MAP_NY;

        return null;
    }

    public TaxRate getTaxRate() {
        return this.taxRate;
    }

}

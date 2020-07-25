package com.github.tivon.shoppingreceipt.mapper;

import com.github.tivon.shoppingreceipt.model.constant.ProductCategory;
import com.github.tivon.shoppingreceipt.model.source.DataSource;

import java.util.HashMap;
import java.util.Map;

public final class ProductCategoryMapper {

    private static final Map<String, ProductCategory> PRODUCT_CATEGORY_MAP = new HashMap<>();

    static {
        PRODUCT_CATEGORY_MAP.put("book", ProductCategory.SALES);
        PRODUCT_CATEGORY_MAP.put("pencil", ProductCategory.SALES);
        PRODUCT_CATEGORY_MAP.put("potato chips", ProductCategory.FOOD);
        PRODUCT_CATEGORY_MAP.put("shirt", ProductCategory.CLOTHING);
    }

    public static DataSource mapProductCategory(DataSource dataSource) {
        if (dataSource == null)
            throw new IllegalArgumentException("Datasource should not be null");

        dataSource.getProducts().forEach(ds -> {
            PRODUCT_CATEGORY_MAP.entrySet().forEach(pc -> {
                if (pc.getKey().equals(ds.getItem()))
                    ds.setCategory(pc.getValue());
            });
        });

        return dataSource;
    }
}

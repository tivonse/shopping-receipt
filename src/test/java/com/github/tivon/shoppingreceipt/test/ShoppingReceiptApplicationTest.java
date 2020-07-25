package com.github.tivon.shoppingreceipt.test;

import com.github.tivon.shoppingreceipt.model.source.DataSource;
import com.github.tivon.shoppingreceipt.service.FileInputService;
import com.github.tivon.shoppingreceipt.service.TaxService;
import com.github.tivon.shoppingreceipt.mapper.ProductCategoryMapper;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

public class ShoppingReceiptApplicationTest {

    static final String USE_CASE_1_FILE_PATH = "src/main/resources/test/use_case_1.json";
    static final String USE_CASE_2_FILE_PATH = "src/main/resources/test/use_case_2.json";
    static final String USE_CASE_3_FILE_PATH = "src/main/resources/test/use_case_3.json";

    DataSource dataSource;
    FileInputService fileInputService;
    TaxService taxService;

    @Before
    public void setup() {
        dataSource = null;
        fileInputService = new FileInputService();
        taxService = new TaxService();
    }

    @Test
    public void test_use_case_1() {
        dataSource = fileInputService.getProductListFromJsonSource(USE_CASE_1_FILE_PATH);
        List<BigDecimal> results = calculatingTax(dataSource);

        assertEquals("21.98", results.get(0).toString());
        assertEquals("1.80", results.get(1).toString());
        assertEquals("23.78", results.get(2).toString());
    }

    @Test
    public void test_use_case_2() {
        dataSource = fileInputService.getProductListFromJsonSource(USE_CASE_2_FILE_PATH);
        List<BigDecimal> results = calculatingTax(dataSource);

        assertEquals("26.96", results.get(0).toString());
        assertEquals("2.40", results.get(1).toString());
        assertEquals("29.36", results.get(2).toString());
    }

    @Test
    public void test_use_case_3() {
        dataSource = fileInputService.getProductListFromJsonSource(USE_CASE_3_FILE_PATH);
        List<BigDecimal> results = calculatingTax(dataSource);

        assertEquals("35.97", results.get(0).toString());
        assertEquals("0.55", results.get(1).toString());
        assertEquals("36.52", results.get(2).toString());
    }

    private List<BigDecimal> calculatingTax(DataSource datasource) {
        dataSource = ProductCategoryMapper.mapProductCategory(dataSource);
        BigDecimal subtotal = taxService.calculateSubTotal(dataSource.getProducts());
        BigDecimal tax = taxService.calculateTax(dataSource.getProducts(), dataSource.getLocation());
        BigDecimal total = taxService.calculateAfterTax(subtotal, tax);

        return Arrays.asList(subtotal, tax, total);
    }

}

package com.github.tivon.shoppingreceipt.main;

import com.github.tivon.shoppingreceipt.model.source.DataSource;
import com.github.tivon.shoppingreceipt.model.Receipt;
import com.github.tivon.shoppingreceipt.service.FileInputService;
import com.github.tivon.shoppingreceipt.service.ReceiptPrintingService;
import com.github.tivon.shoppingreceipt.service.TaxService;
import com.github.tivon.shoppingreceipt.mapper.ProductCategoryMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ShoppingReceiptApplication {

    private static final String KEY_FILE_PATH = "filePath";

    private static final Logger LOGGER = LoggerFactory.getLogger(ShoppingReceiptApplication.class);

    public static void main(String[] args) {

        /*
         proceed to extract data from source file
         */
        FileInputService fileInputService = new FileInputService();

        String filePath = fileInputService.getValueFromPropertiesFileSource().getProperty(KEY_FILE_PATH);
        DataSource dataSource = fileInputService.getProductListFromJsonSource(filePath);
        dataSource = ProductCategoryMapper.mapProductCategory(dataSource);
        LOGGER.info("Finished extracting data from source file %s", KEY_FILE_PATH);

        /*
         proceed to calculate the after-tax
         */
        TaxService taxService = new TaxService();

        Receipt receipt = new Receipt();
        receipt.setSubtotal(taxService.calculateSubTotal(dataSource.getProducts()));
        receipt.setTax(taxService.calculateTax(dataSource.getProducts(), dataSource.getLocation()));
        receipt.setTotal(taxService.calculateAfterTax(receipt.getSubtotal(), receipt.getTax()));
        receipt.setProducts(dataSource.getProducts());
        LOGGER.info("Finished calculating the numbers of tax");

        /*
         proceed to print the receipt
         */
        ReceiptPrintingService receiptPrintingService = new ReceiptPrintingService();
        receiptPrintingService.print(receipt);
        LOGGER.info ("Finished printing the receipt out");
    }

}

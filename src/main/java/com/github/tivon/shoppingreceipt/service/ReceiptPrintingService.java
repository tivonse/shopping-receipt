package com.github.tivon.shoppingreceipt.service;

import com.github.tivon.shoppingreceipt.model.wrapper.StringBufferBucket;
import com.github.tivon.shoppingreceipt.utils.PaddingUtils;
import com.github.tivon.shoppingreceipt.model.Product;
import com.github.tivon.shoppingreceipt.model.Receipt;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

public class ReceiptPrintingService {

    private static final String OUTPUT_FILENAME = "receipt.txt";
    private StringBufferBucket bucket;

    // core functional method to output final string to file
    public void print(Receipt receipt) {

        // create a custom instance perform the String manipulation
        bucket = new StringBufferBucket(new StringBuffer());

        // start to append the text blocks
        appendColumnHeader();
        appendContent(receipt.getProducts());
        appendBalance(receipt.getSubtotal(), receipt.getTax(), receipt.getTotal());

        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(OUTPUT_FILENAME, true));
            writer.append(bucket.endLine().toString());
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private StringBuffer appendColumnHeader() {
        return bucket.padRight("item")
                .padLeft("price")
                .padLeft("qty")
                .newLine();
    }

    private StringBuffer appendContent(List<Product> products) {
        products.forEach(p -> {
            bucket.padRight(p.getItem())
                    .padLeft("$" + p.getPrice().toString())
                    .padLeft(String.valueOf(p.getQuantity()))
                    .newLine();
        });
        return bucket.endLine();
    }

    private StringBuffer appendBalance(BigDecimal subtotal, BigDecimal tax, BigDecimal total) {
        bucket.padRight(PaddingUtils.getColumnDefaultRightMaximumPadding()
                        + PaddingUtils.getColumnDefaultLeftMaximumPadding() * 2
                        - subtotal.toString().length() - 1, "subtotal:")
                .append("$" + subtotal.toString())
                .newLine();
        bucket.padRight(
                PaddingUtils.getColumnDefaultRightMaximumPadding()
                        + PaddingUtils.getColumnDefaultLeftMaximumPadding() * 2
                        - tax.toString().length() - 1,
                "tax:")
                .append("$" + tax.toString())
                .newLine();
        bucket.padRight(
                PaddingUtils.getColumnDefaultRightMaximumPadding()
                        + PaddingUtils.getColumnDefaultLeftMaximumPadding() * 2
                        - total.toString().length() - 1,
                "total:")
                .append("$" + total.toString())
                .newLine();

        return bucket.endLine();
    }

}

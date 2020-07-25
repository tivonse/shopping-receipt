package com.github.tivon.shoppingreceipt.model.source;

import com.github.tivon.shoppingreceipt.model.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DataSource {

    private String location;

    private List<Product> products;

}

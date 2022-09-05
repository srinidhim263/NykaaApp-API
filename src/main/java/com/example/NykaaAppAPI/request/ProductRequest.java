package com.example.NykaaAppAPI.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductRequest {
    private Integer productId;
    private Integer categoryId;
    private String productName;
    private String productPrice;
    private String productDescription;
}

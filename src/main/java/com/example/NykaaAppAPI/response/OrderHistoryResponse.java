package com.example.NykaaAppAPI.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderHistoryResponse {
    private Integer userId;
    private Integer productId;
    private Integer categoryId;
    private String productName;
    private Integer price;
    private String date;
}

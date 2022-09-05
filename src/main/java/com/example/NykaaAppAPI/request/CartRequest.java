package com.example.NykaaAppAPI.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CartRequest {
    private Integer id;
    private  Integer userId;
    private Integer productId;
}

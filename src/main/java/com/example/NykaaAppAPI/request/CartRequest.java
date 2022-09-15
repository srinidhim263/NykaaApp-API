package com.example.NykaaAppAPI.request;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
public class CartRequest {
    private Integer id;
    private  Integer userId;
    private Integer productId;

}

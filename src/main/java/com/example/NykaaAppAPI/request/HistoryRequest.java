package com.example.NykaaAppAPI.request;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Setter
@Getter
@Component
public class HistoryRequest {
    private Integer userId;
    private Integer productId;
    private Integer categoryId;
}

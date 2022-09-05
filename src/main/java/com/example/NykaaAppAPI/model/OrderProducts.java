package com.example.NykaaAppAPI.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Getter
@Setter
public class OrderProducts {
    @Id
    @GeneratedValue
    private Integer orderId;
    private Date orderDate;
    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "userId")
    private NykaaUser user;
    @ManyToOne
    @JoinColumn(name = "product_id", referencedColumnName = "productId")
    private Product product;
}

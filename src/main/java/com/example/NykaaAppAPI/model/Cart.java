package com.example.NykaaAppAPI.model;

import javax.persistence.*;

@Entity
public class Cart {
    @Id
    @GeneratedValue
    private Integer id;
    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "userId")
    private NykaaUser nykaaUser;
    @ManyToOne
    @JoinColumn(name = "product_id", referencedColumnName = "productId")
    private Product product;


}
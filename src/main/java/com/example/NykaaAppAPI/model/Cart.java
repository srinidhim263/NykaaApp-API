package com.example.NykaaAppAPI.model;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Cart {
    @Id
    @GeneratedValue
    private Integer id;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private NykaaUser nykaaUser;

    @ManyToOne
    @JoinColumn(name = "product_id", referencedColumnName = "id")
    private Product product;


}
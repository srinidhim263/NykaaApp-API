package com.example.NykaaAppAPI.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "product")
public class Product {
    @Id
    @GeneratedValue
    private Integer productId;
    private String productName;
    private Integer productPrice;
    private String productDescription;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "category_id", referencedColumnName = "categoryId")
    private Category category;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private Set<Cart> carts;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private Set<OrderProducts> orderProducts;
}

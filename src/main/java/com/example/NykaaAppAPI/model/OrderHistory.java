package com.example.NykaaAppAPI.model;

import com.example.NykaaAppAPI.model.NykaaUser;
import com.example.NykaaAppAPI.model.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "bookingHistory")
public class OrderHistory {
    @Id
    @GeneratedValue
    private Integer id;
    private Integer tickets;

    @ManyToOne
    @JoinColumn(name = "product_id", referencedColumnName = "id")
    private Product product;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "userId")
    private NykaaUser nykaaUser;



    @ManyToOne
    @JoinColumn(name = "category_id", referencedColumnName = "categoryId")
    private Category category;
}
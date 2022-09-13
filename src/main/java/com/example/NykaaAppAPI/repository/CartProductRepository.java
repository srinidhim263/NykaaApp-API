package com.example.NykaaAppAPI.repository;

import com.example.NykaaAppAPI.model.Cart;
import com.example.NykaaAppAPI.model.CartProduct;
import com.example.NykaaAppAPI.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CartProductRepository extends JpaRepository<CartProduct , Integer> {

    Optional<List<CartProduct>> findByProductAndCart(Product product, Cart cart);
    Optional<List<CartProduct>> findByCart(Cart cart);
}

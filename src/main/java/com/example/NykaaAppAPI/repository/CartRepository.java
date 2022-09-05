package com.example.NykaaAppAPI.repository;

import com.example.NykaaAppAPI.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepository extends JpaRepository<Cart , Integer> {
}

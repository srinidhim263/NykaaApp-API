package com.example.NykaaAppAPI.repository;

import com.example.NykaaAppAPI.model.Cart;
import com.example.NykaaAppAPI.model.NykaaUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CartRepository extends JpaRepository<Cart , Integer> {
    Optional<Cart> findByProductUser(NykaaUser nykaaUser);
}

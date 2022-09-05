package com.example.NykaaAppAPI.repository;

import com.example.NykaaAppAPI.model.OrderProducts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<OrderProducts , Integer> {
}

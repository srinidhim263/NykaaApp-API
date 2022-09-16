package com.example.NykaaAppAPI.repository;

import com.example.NykaaAppAPI.model.NykaaUser;
import com.example.NykaaAppAPI.model.OrderHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import java.util.List;

@Repository
public interface OrderHistoryRepository extends JpaRepository<OrderHistory, Integer> {
    List<OrderHistory> findByUser(NykaaUser nykaaUser);
}

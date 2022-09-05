package com.example.NykaaAppAPI.repository;

import com.example.NykaaAppAPI.model.NykaaUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository  extends JpaRepository<NykaaUser , Integer> {
    NykaaUser findByMailId(String mailId);
}

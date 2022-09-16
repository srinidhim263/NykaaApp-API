package com.example.NykaaAppAPI.repository;

import com.example.NykaaAppAPI.model.File;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FileRepository extends JpaRepository<File, Integer> {
}

package com.example.VWorks.Repos;

import com.example.VWorks.DTO.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieRepo extends JpaRepository<Movie, Long> {
    // define custom methods here
    List<Movie> findAll();
}

package com.example.VWorks.Repos;

import com.example.VWorks.DTO.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieRepo extends JpaRepository<Movie, Long> {
    List<Movie> findAll();
    List<Movie> findById(int id);
    List<Movie> findByCategory(String category);
    List<Movie> findByRatingGreaterThan(Double rating);
    List<Movie> findByBoxOffice(Double boxOffice);
    List<Movie> findByReleaseYear(int releaseYear);
    List<Movie> findByCategoryAndReleaseYear(String category, int releaseYear);
}

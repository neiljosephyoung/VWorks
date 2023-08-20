package com.example.VWorks.Service;

import com.example.VWorks.DTO.Movie;
import com.example.VWorks.Repos.MovieRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MovieService {
    @Autowired
    private MovieRepo movieRepo;

    // define methods that use userRepository here
    public List<Movie> getAllMovies() {
        return movieRepo.findAll();
    }
    public Optional<Movie> getMovieById(Long id){
        return movieRepo.findById(id);
    }

    public List<Movie> getMovieByCategory(String category){
        return movieRepo.findByCategory(category);
    }

    public List<Movie> getMovieByReleaseYear(int releaseYear){
        return movieRepo.findByReleaseYear(releaseYear);
    }

    public List<Movie> getMoviesByCategoryAndReleaseYear(String category, Integer releaseYear) {
        return movieRepo.findByCategoryAndReleaseYear(category, releaseYear);
    }
}
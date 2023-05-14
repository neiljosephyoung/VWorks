package com.example.VWorks.Service;

import com.example.VWorks.DTO.Movie;
import com.example.VWorks.Repos.MovieRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieService {
    @Autowired
    private MovieRepo movieRepo;

    // define methods that use userRepository here
    public List<Movie> getAllMovies() {
        return movieRepo.findAll();
    }

}
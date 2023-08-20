package com.example.VWorks.Controller;

import com.example.VWorks.DTO.Movie;
import com.example.VWorks.Service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1")
public class MovieController {
    @Autowired
    private MovieService movieService;

    @GetMapping("/Movies")
    public List<Movie> getMovies(@RequestParam Optional<String> category,
                                 @RequestParam Optional<Integer> releaseYear) {

        System.out.println("Category: " + (category.isPresent() ? category.get() : "no category data found"));
        System.out.println("releaseYear: " + (releaseYear.isPresent() ? releaseYear.get() : "no releaseYear data found"));

        if (category.isPresent() && releaseYear.isPresent()) {
            return movieService.getMoviesByCategoryAndReleaseYear(category.get(), releaseYear.get());
        } else if (category.isPresent()) {
            return movieService.getMovieByCategory(category.get());
        } else if (releaseYear.isPresent()) {
            return movieService.getMovieByReleaseYear(releaseYear.get());
        } else {
            return movieService.getAllMovies();
        }
    }

    @GetMapping("/Movies/{id}")
    public ResponseEntity<Object> getMovieById(@PathVariable Long id) {
        Optional<Movie> movie = movieService.getMovieById(id);
        if (movie.isPresent()) {
            return ResponseEntity.ok(movie.get());
        } else {
            Map<String, String> response = new HashMap<>();
            response.put("error", "Movie not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }




}


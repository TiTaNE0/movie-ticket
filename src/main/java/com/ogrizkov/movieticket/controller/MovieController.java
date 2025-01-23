package com.ogrizkov.movieticket.controller;

import com.ogrizkov.movieticket.dto.MovieDto;
import com.ogrizkov.movieticket.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/movies")
public class MovieController {

    private final MovieService movieService;

    @Autowired
    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    /**
     * Add a new movie.
     */
    @PostMapping
    @Transactional // Ensures atomicity for the add operation
    public ResponseEntity<MovieDto> addMovie(@RequestBody MovieDto movieDto) {
        MovieDto createdMovie = movieService.addMovie(movieDto);
        return new ResponseEntity<>(createdMovie, HttpStatus.CREATED);
    }

    /**
     * Update an existing movie.
     */
    @PutMapping("/{id}")
    @Transactional // Ensures atomicity for the update operation
    public ResponseEntity<MovieDto> updateMovie(@PathVariable Long id, @RequestBody MovieDto movieDto) {
        MovieDto updatedMovie = movieService.updateMovie(id, movieDto);
        return ResponseEntity.ok(updatedMovie);
    }

    /**
     * Delete a movie by ID.
     */
    @DeleteMapping("/{id}")
    @Transactional // Ensures atomicity for the delete operation
    public ResponseEntity<Void> deleteMovie(@PathVariable Long id) {
        movieService.deleteMovie(id);
        return ResponseEntity.noContent().build();
    }

    /**
     * Retrieve all movies.
     */
    @GetMapping
    @Transactional(readOnly = true) // Optimized for read-only operations
    public ResponseEntity<List<MovieDto>> getAllMovies() {
        List<MovieDto> movies = movieService.getAllMovies();
        return ResponseEntity.ok(movies);
    }

    /**
     * Retrieve a specific movie by ID.
     */
    @GetMapping("/{id}")
    @Transactional(readOnly = true) // Optimized for read-only operations
    public ResponseEntity<MovieDto> getMovieById(@PathVariable Long id) {
        MovieDto movie = movieService.getMovieById(id);
        return ResponseEntity.ok(movie);
    }
}

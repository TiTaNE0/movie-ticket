package com.ogrizkov.movieticket.service;

import com.ogrizkov.movieticket.model.Movie;

import java.util.List;

public interface MovieService {

    void addMovie(Movie movie);

    void updateMovie(Movie movie);

    void deleteMovie(int id);

    List<Movie> getAllMovies();

    List<Movie> getMoviesByGenre(String genre);

    List<Movie> getMoviesByRatingFromTo(int from, int to);

    List<Movie> getMoviesByYear(int year);

}

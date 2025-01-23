package com.ogrizkov.movieticket.service;

import com.ogrizkov.movieticket.dto.MovieDto;

import java.util.List;

public interface MovieService {

    MovieDto addMovie(MovieDto MovieDto);

    MovieDto updateMovie(Long id, MovieDto MovieDto);

    void deleteMovie(Long id);

    List<MovieDto> getAllMovies();

    MovieDto getMovieById(Long id);

}

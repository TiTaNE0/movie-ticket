package com.ogrizkov.movieticket.service;

import com.ogrizkov.movieticket.converters.MovieMapper;
import com.ogrizkov.movieticket.dto.MovieDto;
import com.ogrizkov.movieticket.dto.exceptions.ResourceNotFoundException;
import com.ogrizkov.movieticket.model.Movie;
import com.ogrizkov.movieticket.repository.MovieRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional // Class-level annotation for default transactional behavior
public class MovieServiceImpl implements MovieService {

    private final MovieRepository movieRepository;
    private final MovieMapper movieMapper;

    public MovieServiceImpl(MovieRepository movieRepository, MovieMapper movieMapper) {
        this.movieRepository = movieRepository;
        this.movieMapper = movieMapper;
    }

    @Override
    public MovieDto addMovie(MovieDto MovieDto) {
        Movie movie = movieMapper.toEntity(MovieDto);
        Movie savedMovie = movieRepository.save(movie);
        return movieMapper.toDto(savedMovie);
    }

    @Override
    public MovieDto updateMovie(Long id, MovieDto MovieDto) {
        Movie existingMovie = movieRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Movie not found with id: " + id));
        movieMapper.updateMovieFromDto(MovieDto, existingMovie);
        Movie updatedMovie = movieRepository.save(existingMovie);
        return movieMapper.toDto(updatedMovie);
    }

    @Override
    public void deleteMovie(Long id) {
        if (!movieRepository.existsById(id)) {
            throw new ResourceNotFoundException("Movie not found with id: " + id);
        }
        movieRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<MovieDto> getAllMovies() {
        List<Movie> movies = movieRepository.findAll();
        return movies.stream()
                .map(movieMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public MovieDto getMovieById(Long id) {
        Movie movie = movieRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Movie not found with id: " + id));
        return movieMapper.toDto(movie);
    }

}


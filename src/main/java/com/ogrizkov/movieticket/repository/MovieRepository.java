package com.ogrizkov.movieticket.repository;

import com.ogrizkov.movieticket.model.Movie;
import com.ogrizkov.movieticket.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {

}

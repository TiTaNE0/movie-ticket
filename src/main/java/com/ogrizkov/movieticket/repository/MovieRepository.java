package com.ogrizkov.movieticket.repository;

import com.ogrizkov.movieticket.model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {

}

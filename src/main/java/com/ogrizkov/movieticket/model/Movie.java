package com.ogrizkov.movieticket.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @Setter
    String title;
    @Setter
    String genre;
    @Setter
    int duration;
    @Setter
    int rating;
    @Setter
    int releaseYear;


    public Movie(String title, String genre, int duration, int rating, int releaseYear) {
        this.title = title;
        this.genre = genre;
        this.duration = duration;
        this.rating = rating;
        this.releaseYear = releaseYear;
    }
}

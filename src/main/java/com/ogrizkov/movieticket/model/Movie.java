package com.ogrizkov.movieticket.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.io.Serializable;

@Entity
public record Movie(
        @Id
        int id,
        String title,
        String Genre,
        int duration,
        int rating,
        int releaseYear
    ) implements Serializable {

}

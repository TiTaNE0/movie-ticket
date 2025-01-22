package com.ogrizkov.movieticket.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.io.Serializable;

@Entity
public record Showtime(
                @Id
                int id,
                int movieId,
                String theater,
                int seats,
                int startTime,
                int endTime
    ) implements Serializable {
}

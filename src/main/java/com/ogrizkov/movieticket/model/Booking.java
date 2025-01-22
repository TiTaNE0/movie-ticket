package com.ogrizkov.movieticket.model;
import com.ogrizkov.user.User;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.io.Serializable;

@Entity
public record Booking(
        @Id
        int id,
        String userEmail,
        int movieId,
        int showtimeId,
        int seatNumber,
        int price
    ) implements Serializable {
}

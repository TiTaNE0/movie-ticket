package com.ogrizkov.movieticket.model;
import com.ogrizkov.user.User;

public record Booking(
        int id,
        User user,
        Movie movie,
        Showtime showtime,
        int seatNumber,
        int price
) {
}

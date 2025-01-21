package com.ogrizkov.movieticket.model;

public record Showtime(
                int id,
                Movie movie,
                String theater,
                int seats,
                int startTime,
                int endTime) {
}

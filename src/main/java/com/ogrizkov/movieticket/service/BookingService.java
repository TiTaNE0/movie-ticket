package com.ogrizkov.movieticket.service;

public interface BookingService {

    void bookShowtime(int id);

    void getBookingById(int id);

    void getBookingByEmail(String email);


}

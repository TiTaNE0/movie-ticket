package com.ogrizkov.user;


public record User(
    int id,
    String firstName,
    String lastName,
    String email,
    String password,
    boolean admin
    ) {
}

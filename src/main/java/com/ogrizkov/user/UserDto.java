package com.ogrizkov.user;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

    private Long id;
    private String name;
    private String email;
    private String password; // Note: This should only be used for input, never for output
    private UserRole role;

    // Constructor without id and password for safe object creation
    public UserDto(String name, String email, UserRole role) {
        this.name = name;
        this.email = email;
        this.role = role;
    }
}

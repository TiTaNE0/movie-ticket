package com.ogrizkov.user;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "users")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class User {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        Long id;
        String name;
        String email;
        String password;
        boolean admin;
}

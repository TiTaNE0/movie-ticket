package com.ogrizkov.user;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "users")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        Long id;

        @Column(nullable = false)
        String name;

        @Column(nullable = false, unique = true)
        String email;

        @Column(nullable = false)
        String password;

        @Column(nullable = false)
        @Enumerated(EnumType.STRING)
        private UserRole role;
}

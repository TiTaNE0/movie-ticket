package com.ogrizkov.user;


import java.util.List;
import java.util.Optional;

public interface UserService {

    User registerUser(User user);

    Optional<User> authenticateUser(String email, String password);

    List<User> getAllUsers();

    Optional<User> getUserById(Long id);

    Optional<User> updateUser(Long id, User user);

    void deleteUser(Long id);

    boolean isEmailUnique(String email);
}

package com.cibertec.login.services;

import java.util.List;

import com.cibertec.login.entities.User;

public interface UserService {
    
    List<User> findAll();

    User save(User user);

    boolean existsByUsername(String username);
}

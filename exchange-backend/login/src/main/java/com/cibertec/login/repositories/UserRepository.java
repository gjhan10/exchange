package com.cibertec.login.repositories;

import java.util.Optional;

import com.cibertec.login.entities.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long>{
    boolean existsByUsername(String username);

    Optional<User> findByUsername(String username);
}

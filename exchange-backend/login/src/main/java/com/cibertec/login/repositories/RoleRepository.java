package com.cibertec.login.repositories;

import java.util.Optional;

import com.cibertec.login.entities.Role;
import org.springframework.data.repository.CrudRepository;

public interface RoleRepository extends CrudRepository<Role, Long> {

    Optional<Role> findByName(String name);
    
}

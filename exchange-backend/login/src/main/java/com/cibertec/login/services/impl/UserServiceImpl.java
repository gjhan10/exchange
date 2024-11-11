package com.cibertec.login.services.impl;

import com.cibertec.login.entities.Role;
import com.cibertec.login.entities.User;
import com.cibertec.login.repositories.RoleRepository;
import com.cibertec.login.repositories.UserRepository;
import com.cibertec.login.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {


    private final UserRepository repository;

    private final RoleRepository roleRepository;

    private final PasswordEncoder passwordEncoder;

    @Override
    @Transactional(readOnly = true)
    public List<User> findAll() {
        return (List<User>) repository.findAll();
    }

    @Override
    @Transactional
    public User save(User user) {

        List<Role> roles = new ArrayList<>();

        addRoleIfPresent(roles, "ROLE_USER", true); // Siempre añade ROLE_USER
        addRoleIfPresent(roles, "ROLE_ADMIN", user.isAdmin()); // Añade ROLE_ADMIN solo si el usuario es admin

        user.setRoles(roles);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setEnabled(true);
        return repository.save(user);

    }

    @Override
    public boolean existsByUsername(String username) {
        return repository.existsByUsername(username);
    }

    private void addRoleIfPresent(List<Role> roles, String roleName, boolean condition) {
        roleRepository.findByName(roleName)
                .filter(role -> condition)
                .ifPresent(roles::add);
    }

}

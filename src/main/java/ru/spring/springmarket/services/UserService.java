package ru.spring.springmarket.services;

import org.springframework.security.core.userdetails.UserDetailsService;
import ru.spring.springmarket.model.User;

import java.util.Optional;

public interface UserService extends UserDetailsService {
    public Optional<User> findByUsername(String username);
}

package com.unla.grupo12OO22024.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.unla.grupo12OO22024.models.User;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
}

package com.idealtrip.idealTrip.repository;

import java.util.List;
import java.util.Optional;

import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.idealtrip.idealTrip.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByName(String name);

    Optional<User> findByEmail(String email);

    List<String> findRolesByName(String name);

    @Query("SELECT u FROM User u WHERE u.username = ?1")
    public User findByUsername(String username);
}

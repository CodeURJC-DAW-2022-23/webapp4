package com.idealtrip.idealTrip.repository;

import java.util.List;
import java.util.Optional;

import javax.management.Query;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.idealtrip.idealTrip.model.Review;
import com.idealtrip.idealTrip.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByName(String name);

    Optional<User> findByEmail(String email);

    List<String> findRolesByName(String name);

    // @Query("")
    // List<Review> findReviewsOfUser(Long userId, Pageable pageable);

}

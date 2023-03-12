package com.idealtrip.idealTrip.repository;

import java.util.List;
import java.util.Optional;

import com.idealtrip.idealTrip.model.Destination;
import com.idealtrip.idealTrip.model.House;
import com.idealtrip.idealTrip.service.UserService;
import org.springframework.data.jpa.repository.JpaRepository;

import com.idealtrip.idealTrip.model.Purchase;
import com.idealtrip.idealTrip.model.User;

public interface PurchaseRepository extends JpaRepository<Purchase, Long> {

    List<Purchase> findByUser(User user);
    Optional<House> findDById(Long id);
}

package com.idealtrip.idealTrip.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.idealtrip.idealTrip.model.Purchase;
import com.idealtrip.idealTrip.model.User;

public interface PurchaseRepository extends JpaRepository<Purchase, Long> {
    List<Purchase> findByUser(User user);
    
}

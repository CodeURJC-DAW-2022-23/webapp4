package com.idealtrip.idealTrip.service;

import java.util.List;
import java.util.Optional;

import com.idealtrip.idealTrip.model.House;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.idealtrip.idealTrip.model.Purchase;
import com.idealtrip.idealTrip.repository.PurchaseRepository;

@Service
public class PurchaseService {

    @Autowired
    private PurchaseRepository purchases;

    public void save(Purchase purchase) {
        purchases.save(purchase);
    }

    public void deleteById(long id) {
        purchases.deleteById(id);
    }

    public Optional<Purchase> findById(long id) {
        return purchases.findById(id);
    }

}

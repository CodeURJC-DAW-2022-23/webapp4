package com.idealtrip.idealTrip.repository;

import com.idealtrip.idealTrip.model.Newsletter;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NewsletterRepository extends JpaRepository<Newsletter, Long> {
    // Optional<Newsletter> findByName(String email);

    // Optional<Newsletter> findByEmail(String email);
}

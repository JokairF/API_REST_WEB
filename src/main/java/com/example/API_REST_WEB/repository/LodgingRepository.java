package com.example.API_REST_WEB.repository;


import com.example.API_REST_WEB.entity.Lodging;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LodgingRepository extends JpaRepository<Lodging, Long> {
    @Query("SELECT l FROM Lodging l WHERE l.available = true")
    List<Lodging> findByAvailableTrue();

    @Query("SELECT l FROM Lodging l WHERE l.address LIKE %:address%")
    List<Lodging> findByAddressContainingIgnoreCase(String address);

    @Query("SELECT l FROM Lodging l WHERE l.capacity >= :guestCount AND l.available = true")
    List<Lodging> findAvailableLodgingsForGuests(int guestCount);

    @Query("SELECT l FROM Lodging l WHERE l.pricePerNight BETWEEN :minPrice AND :maxPrice")
    List<Lodging> findByPriceRange(double minPrice, double maxPrice);
}
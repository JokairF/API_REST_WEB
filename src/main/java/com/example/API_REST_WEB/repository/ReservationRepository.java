package com.example.API_REST_WEB.repository;

import com.example.API_REST_WEB.entity.Lodging;
import com.example.API_REST_WEB.entity.Reservation;
import com.example.API_REST_WEB.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    List<Reservation> findByUser(User user);
    List<Reservation> findByLodging(Lodging lodging);
    List<Reservation> findByStatus(Reservation.ReservationStatus status);

    @Query("SELECT r FROM Reservation r WHERE r.lodging.id = :lodgingId AND " +
            "((r.checkInDate BETWEEN :checkIn AND :checkOut) OR " +
            "(r.checkOutDate BETWEEN :checkIn AND :checkOut) OR " +
            "(:checkIn BETWEEN r.checkInDate AND r.checkOutDate) OR " +
            "(:checkOut BETWEEN r.checkInDate AND r.checkOutDate)) AND " +
            "r.status <> com.example.API_REST_WEB.entity.Reservation.ReservationStatus.CANCELLED")
    List<Reservation> findOverlappingReservations(Long lodgingId, LocalDate checkIn, LocalDate checkOut);

    @Query("SELECT COUNT(r) FROM Reservation r WHERE r.lodging.id = :lodgingId AND " +
            "r.status = com.example.API_REST_WEB.entity.Reservation.ReservationStatus.PENDING")
    long countPendingReservationsByLodging(Long lodgingId);

    @Query("SELECT COUNT(r) FROM Reservation r WHERE r.user.id = :userId AND " +
            "r.status = com.example.API_REST_WEB.entity.Reservation.ReservationStatus.CONFIRMED")
    long countActiveReservationsByUser(Long userId);
}
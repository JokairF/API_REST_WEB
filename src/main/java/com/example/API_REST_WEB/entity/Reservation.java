package com.example.API_REST_WEB.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "reservations")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "lodging_id", nullable = false)
    private Lodging lodging;

    @Column(nullable = false)
    private LocalDate checkInDate;

    @Column(nullable = false)
    private LocalDate checkOutDate;

    @Column(nullable = false)
    private Integer numberOfGuests;

    @Column(nullable = false)
    private Boolean isCancelled = false;

    @Column(nullable = false)
    private String status;

    public static class ReservationStatus {
        public static final String PENDING = "PENDING";
        public static final String CONFIRMED = "CONFIRMED";
        public static final String CANCELLED = "CANCELLED";
    }
}

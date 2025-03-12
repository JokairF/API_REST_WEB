package com.example.API_REST_WEB.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LodgingResponse {
    private Long id;
    private String title;
    private String description;
    private String address;
    private BigDecimal pricePerNight;
    private Integer numberOfRooms;
    private Integer numberOfBathrooms;
    private Integer capacity;
    private Boolean hasWifi;
    private Boolean hasParking;
    private Boolean hasPool;
    private Boolean hasAirConditioning;
    private String ownerUsername;
    private Double averageRating;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
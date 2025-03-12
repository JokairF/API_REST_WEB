package com.example.API_REST_WEB.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LodgingRequest {
    @NotBlank(message = "Le titre est obligatoire")
    private String title;

    @NotBlank(message = "La description est obligatoire")
    private String description;

    @NotBlank(message = "L'adresse est obligatoire")
    private String address;

    @NotNull(message = "Le prix est obligatoire")
    @Positive(message = "Le prix doit être positif")
    private BigDecimal pricePerNight;

    @NotNull(message = "Le nombre de chambres est obligatoire")
    @Positive(message = "Le nombre de chambres doit être positif")
    private Integer numberOfRooms;

    @NotNull(message = "Le nombre de salles de bain est obligatoire")
    @Positive(message = "Le nombre de salles de bain doit être positif")
    private Integer numberOfBathrooms;

    @NotNull(message = "La capacité est obligatoire")
    @Positive(message = "La capacité doit être positive")
    private Integer capacity;

    private Boolean hasWifi;
    private Boolean hasParking;
    private Boolean hasPool;
    private Boolean hasAirConditioning;
}

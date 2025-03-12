package com.example.API_REST_WEB.dto.request;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReservationRequest {
    @NotNull(message = "L'ID du logement est obligatoire")
    private Long lodgingId;

    @NotNull(message = "La date d'arrivée est obligatoire")
    @Future(message = "La date d'arrivée doit être dans le futur")
    private LocalDate checkInDate;

    @NotNull(message = "La date de départ est obligatoire")
    @Future(message = "La date de départ doit être dans le futur")
    private LocalDate checkOutDate;

    @NotNull(message = "Le nombre d'invités est obligatoire")
    private Integer numberOfGuests;

    private String specialRequests;
}
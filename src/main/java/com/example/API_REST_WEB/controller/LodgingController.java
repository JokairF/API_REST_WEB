package com.example.API_REST_WEB.controller;

import com.example.API_REST_WEB.entity.Lodging;
import com.example.API_REST_WEB.service.LodgingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/lodgings")
@RequiredArgsConstructor
public class LodgingController {
    private final LodgingService lodgingService;

    @GetMapping
    public ResponseEntity<List<Lodging>> getAllLodgings() {
        return ResponseEntity.ok(lodgingService.getAllLodgings());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Lodging> getLodgingById(@PathVariable Long id) {
        return ResponseEntity.ok(lodgingService.getLodgingById(id));
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Lodging> createLodging(@RequestBody Lodging lodging) {
        return ResponseEntity.status(HttpStatus.CREATED).body(lodgingService.createLodging(lodging));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Lodging> updateLodging(@PathVariable Long id, @RequestBody Lodging lodging) {
        return ResponseEntity.ok(lodgingService.updateLodging(id, lodging));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteLodging(@PathVariable Long id) {
        lodgingService.deleteLodging(id);
        return ResponseEntity.noContent().build();
    }
}

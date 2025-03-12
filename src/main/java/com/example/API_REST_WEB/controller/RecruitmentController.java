package com.example.API_REST_WEB.controller;

import com.example.API_REST_WEB.entity.RecruitmentApplication;
import com.example.API_REST_WEB.service.RecruitmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/recruitment")
@RequiredArgsConstructor
public class RecruitmentController {
    private final RecruitmentService recruitmentService;

    @PostMapping
    public ResponseEntity<RecruitmentApplication> submitApplication(@RequestBody RecruitmentApplication application) {
        return ResponseEntity.status(HttpStatus.CREATED).body(recruitmentService.submitApplication(application));
    }
}

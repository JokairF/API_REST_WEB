package com.example.API_REST_WEB.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RecruitmentResponse {
    private Long id;
    private String fullName;
    private String email;
    private String phoneNumber;
    private String position;
    private Integer yearsOfExperience;
    private String status; // SUBMITTED, UNDER_REVIEW, ACCEPTED, REJECTED
    private String coverLetter;
    private String resumeUrl;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
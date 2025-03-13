package com.example.API_REST_WEB.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "recruitment_applications")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RecruitmentApplication {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String fullName;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String phone;

    @Column(nullable = false)
    private String resumeUrl;

    @Column(nullable = false)
    private String coverLetter;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private RecruitmentStatus status;

    @Column(nullable = false)
    private String comments;

    @Column(nullable = false)
    private LocalDateTime submissionDate;

    public enum RecruitmentStatus {
        PENDING,
        ACCEPTED,
        REJECTED
    }
}

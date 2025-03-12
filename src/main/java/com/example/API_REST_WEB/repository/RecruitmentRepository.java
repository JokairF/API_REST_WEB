package com.example.API_REST_WEB.repository;

import com.example.API_REST_WEB.entity.RecruitmentApplication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface RecruitmentRepository extends JpaRepository<RecruitmentApplication, Long> {
    Optional<RecruitmentApplication> findByEmail(String email);
    List<RecruitmentApplication> findByStatus(RecruitmentApplication.ApplicationStatus status);
    List<RecruitmentApplication> findByPosition(String position);
    List<RecruitmentApplication> findBySubmissionDateBetween(LocalDateTime start, LocalDateTime end);
}
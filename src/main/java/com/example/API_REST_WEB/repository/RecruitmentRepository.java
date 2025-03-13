package com.example.API_REST_WEB.repository;

import com.example.API_REST_WEB.entity.RecruitmentApplication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface RecruitmentRepository extends JpaRepository<RecruitmentApplication, Long> {
    Optional<RecruitmentApplication> findByEmail(String email);
    List<RecruitmentApplication> findByStatus(RecruitmentApplication.RecruitmentStatus status);
    List<RecruitmentApplication> findBySubmissionDateBetween(LocalDateTime start, LocalDateTime end);

    @Query("SELECT COUNT(a) FROM RecruitmentApplication a WHERE a.status = com.example.API_REST_WEB.entity.RecruitmentApplication.RecruitmentStatus.PENDING")
    long countPendingApplications();

    @Query("SELECT COUNT(a) FROM RecruitmentApplication a WHERE a.status = com.example.API_REST_WEB.entity.RecruitmentApplication.RecruitmentStatus.ACCEPTED")
    long countAcceptedApplications();

    @Query("SELECT COUNT(r) FROM RecruitmentApplication r WHERE r.status = :REJECTED")
    long countRejectedApplications(@Param("status") RecruitmentApplication.RecruitmentStatus status);
}
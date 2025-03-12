package com.example.API_REST_WEB.service;

import com.example.API_REST_WEB.entity.RecruitmentApplication;
import com.example.API_REST_WEB.repository.RecruitmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RecruitmentService {
    private final RecruitmentRepository recruitmentRepository;

    public RecruitmentApplication submitApplication(RecruitmentApplication application) {
        return recruitmentRepository.save(application);
    }
}

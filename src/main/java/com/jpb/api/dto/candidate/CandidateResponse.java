package com.jpb.api.dto.candidate;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CandidateResponse {

    private Long id;

    private String firstName;

    private String lastName;

    private String phoneNumber;

    private String city;

    private String skills;

    private Integer experienceYears;

    private String qualification;

    private String resumeUrl;

    private String linkedinUrl;

    private String githubUrl;

    private Boolean active;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    private Long userId;

    private String email;
}
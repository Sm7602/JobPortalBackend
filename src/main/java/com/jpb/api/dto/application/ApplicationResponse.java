package com.jpb.api.dto.application;

import java.time.LocalDate;
import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ApplicationResponse {

    private Long id;

    private LocalDate appliedDate;

    private String status;

    private String coverLetter;

    private Boolean active;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    private Long candidateId;

    private String candidateName;

    private Long jobId;

    private String jobTitle;
}

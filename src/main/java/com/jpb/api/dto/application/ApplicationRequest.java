package com.jpb.api.dto.application;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ApplicationRequest {

    @NotNull(message = "Job id is required")
    private Long jobId;

    @NotBlank(message = "Cover letter is required")
    private String coverLetter;
}

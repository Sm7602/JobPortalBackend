package com.jpb.api.dto.application;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ApplicationUpdateRequest {

    @NotBlank
    private String status;

    @NotBlank
    private String coverLetter;

}

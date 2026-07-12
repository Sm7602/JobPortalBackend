package com.jpb.api.dto.job;

import java.math.BigDecimal;
import java.time.LocalDate;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class JobRequest {

    @NotBlank(message = "Job title is required")
    private String title;

    @NotBlank(message = "Job description is required")
    private String description;

    @NotNull(message = "Salary is required")
    @DecimalMin(value = "0.0", inclusive = false)
    private BigDecimal salary;

    @NotBlank(message = "Location is required")
    private String location;

    @NotNull(message = "Vacancies are required")
    @Min(1)
    private Integer vacancies;

    @NotNull(message = "Experience is required")
    @Min(0)
    private Integer experienceRequired;

    @NotBlank(message = "Skills are required")
    private String skillsRequired;

    @NotBlank(message = "Job type is required")
    private String jobType;

    @NotNull(message = "Application deadline is required")
    private LocalDate applicationDeadline;

    @NotNull(message = "Company ID is required")
    private Long companyId;
}

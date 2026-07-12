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
public class JobUpdateRequest {

    @NotBlank
    private String title;

    @NotBlank
    private String description;

    @NotNull
    @DecimalMin(value = "0.0", inclusive = false)
    private BigDecimal salary;

    @NotBlank
    private String location;

    @NotNull
    @Min(1)
    private Integer vacancies;

    @NotNull
    @Min(0)
    private Integer experienceRequired;

    @NotBlank
    private String skillsRequired;

    @NotBlank
    private String jobType;

    @NotNull
    private LocalDate applicationDeadline;
}

package com.jpb.api.dto.job;


import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class JobResponse {

    private Long id;

    private String title;

    private String description;

    private BigDecimal salary;

    private String location;

    private Integer vacancies;

    private Integer experienceRequired;

    private String skillsRequired;

    private String jobType;

    private LocalDate applicationDeadline;

    private Boolean active;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    private Long companyId;

    private String companyName;
}

package com.jpb.api.dto.company;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CompanyResponse {

    private Long id;

    private String companyName;

    private String phoneNumber;

    private String website;

    private String industry;

    private String location;

    private String description;

    private String logoUrl;

    private Boolean active;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    private Long userId;

    private String email;
}

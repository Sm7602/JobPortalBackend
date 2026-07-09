package com.jpb.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CompanyRegisterRequest {

	private String companyName;

	private String email;

    private String password;

    private String phoneNumber;

    private String website;

    private String industry;

    private String location;

    private String description;

    private String logoUrl;
}

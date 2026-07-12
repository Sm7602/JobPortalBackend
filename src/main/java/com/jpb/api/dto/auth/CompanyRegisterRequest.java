package com.jpb.api.dto.auth;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class CompanyRegisterRequest extends BaseRegisterRequest {

	    @NotBlank
	    private String companyName;

	    private String website;

	    private String industry;

	    private String location;

	    private String description;

	    private String logoUrl;

}

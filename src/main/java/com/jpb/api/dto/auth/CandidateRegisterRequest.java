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
public class CandidateRegisterRequest extends BaseRegisterRequest {

	   @NotBlank
	    private String firstName;

	    @NotBlank
	    private String lastName;

	    private String city;

	    private String skills;

	    private Integer experienceYears;

	    private String qualification;

	    private String resumeUrl;

	    private String linkedinUrl;

	    private String githubUrl;

	    
}

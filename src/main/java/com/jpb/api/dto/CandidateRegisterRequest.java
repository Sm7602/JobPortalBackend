package com.jpb.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CandidateRegisterRequest {

	    private String firstName;

	    private String lastName;

	    private String email;

	    private String password;

	    private String phoneNumber;

	    private String city;

	    private String skills;

	    private Integer experienceYears;

	    private String qualification;

	    private String resumeUrl;

	    private String linkedinUrl;

	    private String githubUrl;
}

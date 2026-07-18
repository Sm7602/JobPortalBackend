package com.jpb.api.service;

import java.time.LocalDateTime;
import java.util.HashMap;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.jpb.api.dao.AdminRepository;
import com.jpb.api.dao.CandidateRepository;
import com.jpb.api.dao.CompanyRepository;
import com.jpb.api.dao.UserRepository;
import com.jpb.api.dto.auth.AdminRegisterRequest;
import com.jpb.api.dto.auth.AuthenticationResponse;
import com.jpb.api.dto.auth.CandidateRegisterRequest;
import com.jpb.api.dto.auth.CompanyRegisterRequest;
import com.jpb.api.dto.auth.LoginRequest;
import com.jpb.api.entity.Admin;
import com.jpb.api.entity.Candidate;
import com.jpb.api.entity.Company;
import com.jpb.api.entity.Role;
import com.jpb.api.entity.User;
import com.jpb.api.security.JwtService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import lombok.RequiredArgsConstructor;
import lombok.var;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

	private final UserRepository userRepository;
	
	private final CandidateRepository candidateRepository;
	
	private final CompanyRepository CompanyRepository ;
	
	private final AdminRepository adminRepository;

	private final PasswordEncoder passwordEncoder;
	
	private final JwtService jwtService;
	
	private final AuthenticationManager authenticationManager;
	
	public AuthenticationResponse registerCandidate(CandidateRegisterRequest request) {
         System.out.println("AuthenticationService.registerCandidate()");
        
		 if (userRepository.findByEmail(request.getEmail()).isPresent()) {
		        throw new RuntimeException("Email is already registered. Please login.");
		    }
		
	    User user = User.builder()
	            .firstname(request.getFirstName())
	            .lastname(request.getLastName())
	            .email(request.getEmail())
	            .password(passwordEncoder.encode(request.getPassword()))
	            .role(Role.CANDIDATE)
	            .build();

	    user = userRepository.save(user);

	    Candidate candidate = Candidate.builder()
	    		    .firstName(request.getFirstName())
	            .lastName(request.getLastName())
                .phoneNumber(request.getPhoneNumber())
                .city(request.getCity())
                .skills(request.getSkills())
                .experienceYears(request.getExperienceYears())
                .qualification(request.getQualification())
                .resumeUrl(request.getResumeUrl())
                .linkedinUrl(request.getLinkedinUrl())
                .githubUrl(request.getGithubUrl())
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .active(true)
                .user(user)
                .build();

	    candidateRepository.save(candidate);

	    String jwtToken = jwtService.generateToken(new HashMap<>(), user);

	    return AuthenticationResponse.builder()
	    		    .token(jwtToken)
	    	        .tokenType("Bearer")
	    	        .userId(user.getId())
	    	        .email(user.getEmail())
	    	        .role(user.getRole().name())
	    	        .message("Registration successful")
	    	        .build();
	}
	
	public AuthenticationResponse registerAdmin(AdminRegisterRequest request) {

         System.out.println("AuthenticationService.registerCandidate()");

		 if (userRepository.findByEmail(request.getEmail()).isPresent()) {
		        throw new RuntimeException("Email is already registered. Please login.");
		    }
		
	    User user = User.builder()
	    		    .firstname(request.getFirstName())
	            .lastname(request.getLastName())
	            .email(request.getEmail())
	            .password(passwordEncoder.encode(request.getPassword()))
	            .role(Role.ADMIN)
	            .build();

	    user = userRepository.save(user);

	    Admin admin = Admin.builder()
	    		    .firstName(request.getFirstName())
	            .lastName(request.getLastName())
                .phoneNumber(request.getPhoneNumber())
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .active(true)
                .user(user)
	            .build();

	    adminRepository.save(admin);

	    String jwtToken = jwtService.generateToken(new HashMap<>(), user);

	    return AuthenticationResponse.builder()
    		    .token(jwtToken)
    	        .tokenType("Bearer")
    	        .userId(user.getId())
    	        .email(user.getEmail())
    	        .role(user.getRole().name())
    	        .message("Registration successful")
    	        .build();
	}
	
	
	public AuthenticationResponse registerCompany(CompanyRegisterRequest request) {

		 if (userRepository.findByEmail(request.getEmail()).isPresent()) {
		        throw new RuntimeException("Email is already registered. Please login.");
		    }
		
	    User user = User.builder()
	            .firstname(request.getCompanyName())
	            .lastname("")
	            .email(request.getEmail())
	            .password(passwordEncoder.encode(request.getPassword()))
	            .role(Role.COMPANY)
	            .build();

	    user = userRepository.save(user);

	   Company company = Company.builder()
	    		    .companyName(request.getCompanyName())
                .phoneNumber(request.getPhoneNumber())
                .website(request.getWebsite())
                .industry(request.getIndustry())
                .location(request.getLocation())
                .description(request.getDescription())
                .logoUrl(request.getLogoUrl())
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .active(true)
                .user(user)
	            .build();

	   CompanyRepository.save(company);

	    String jwtToken = jwtService.generateToken(new HashMap<>(), user);

	    return AuthenticationResponse.builder()
    		    .token(jwtToken)
    	        .tokenType("Bearer")
    	        .userId(user.getId())
    	        .email(user.getEmail())
    	        .role(user.getRole().name())
    	        .message("Registration successful")
    	        .build();
	}
	
	public AuthenticationResponse authenticate(LoginRequest request) {
		authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(
						request.getEmail()
						,request.getPassword()));
		
		var user =userRepository.findByEmail(request.getEmail())
				.orElseThrow();
		var jwtToken =jwtService.generaTetoken(user);
		
		return AuthenticationResponse.builder()
    		    .token(jwtToken)
    	        .tokenType("Bearer")
    	        .userId(user.getId())
    	        .email(user.getEmail())
    	        .role(user.getRole().name())
    	        .message("Login successful")
    	        .build();
	}

}


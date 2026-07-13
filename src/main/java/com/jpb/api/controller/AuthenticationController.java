package com.jpb.api.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jpb.api.dto.auth.AdminRegisterRequest;
import com.jpb.api.dto.auth.AuthenticationResponse;
import com.jpb.api.dto.auth.CandidateRegisterRequest;
import com.jpb.api.dto.auth.CompanyRegisterRequest;
import com.jpb.api.dto.auth.LoginRequest;
import com.jpb.api.service.AuthenticationService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {
	
	private final AuthenticationService service;

	@PostMapping("/registerCompany")
	public ResponseEntity<AuthenticationResponse> registerCompany(
			@RequestBody CompanyRegisterRequest request){
		System.out.println("AuthgenticationController.registerCompany()");
		return ResponseEntity.ok(service.registerCompany(request));
	}
	
	@PostMapping("/registerAdmin")
	public ResponseEntity<AuthenticationResponse> registerAdmin(
			@RequestBody AdminRegisterRequest request){
		System.out.println("AuthgenticationController.registerAdmin()");
		return ResponseEntity.ok(service.registerAdmin(request));
	}
	
	@PostMapping("/registerCandidate")
	public ResponseEntity<AuthenticationResponse> registerCandidate(
			@RequestBody CandidateRegisterRequest request){
		System.out.println("AuthgenticationController.registerCandidate()");
		return ResponseEntity.ok(service.registerCandidate(request));
	}
	
	@PostMapping("/authenticate")
	public ResponseEntity<AuthenticationResponse> authenticate(
			@RequestBody LoginRequest request){
		System.out.println("AuthgenticationController login.....");
		return ResponseEntity.ok(service.authenticate(request));
	}
}


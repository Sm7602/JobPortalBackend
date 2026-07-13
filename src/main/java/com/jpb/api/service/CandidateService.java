package com.jpb.api.service;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.jpb.api.dao.CandidateRepository;
import com.jpb.api.dto.candidate.CandidateRequest;
import com.jpb.api.dto.candidate.CandidateResponse;
import com.jpb.api.dto.candidate.CandidateUpdateRequest;
import com.jpb.api.entity.Candidate;

@Service
public class CandidateService {

    @Autowired
    private CandidateRepository candidateRepository;
    
    private CandidateResponse convertToResponse(Candidate candidate) {

        return CandidateResponse.builder()
                .id(candidate.getId())
                .firstName(candidate.getFirstName())
                .lastName(candidate.getLastName())
                .phoneNumber(candidate.getPhoneNumber())
                .city(candidate.getCity())
                .skills(candidate.getSkills())
                .experienceYears(candidate.getExperienceYears())
                .qualification(candidate.getQualification())
                .resumeUrl(candidate.getResumeUrl())
                .linkedinUrl(candidate.getLinkedinUrl())
                .githubUrl(candidate.getGithubUrl())
                .active(candidate.getActive())
                .createdAt(candidate.getCreatedAt())
                .updatedAt(candidate.getUpdatedAt())
                .userId(candidate.getUser().getId())
                .email(candidate.getUser().getEmail())
                .build();
    }

    public CandidateResponse saveCandidate(CandidateRequest request) {
        System.out.println("CandidateService.saveCandidate()");
         Candidate candidate= Candidate.builder()
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
                 .build();
        candidate= candidateRepository.save(candidate);
        
        return convertToResponse(candidate);
    }

    public List<CandidateResponse> getAllCandidates() {
        System.out.println("CandidateService.getAllCandidates()");
        return candidateRepository.findAll()
        		    .stream()
 	            .map(this::convertToResponse)
 	            .toList();
    }

    public CandidateResponse getCandidateById(Long id) {
        System.out.println("CandidateService.getCandidateById()");
        Candidate candidate= candidateRepository.findById(id).orElseThrow(() ->
                        new RuntimeException("Candidate not found"));
        return convertToResponse(candidate);
    }

    public CandidateResponse updateCandidate(Long id, CandidateUpdateRequest request) {
        System.out.println("CandidateService.updateCandidate()");
        Candidate candidate = candidateRepository.findById(id).orElseThrow(() ->
                        new RuntimeException("Candidate not found with id : " + id));

        candidate.setFirstName(request.getFirstName());
        candidate.setLastName(request.getLastName());
        candidate.setPhoneNumber(request.getPhoneNumber());
        candidate.setCity(request.getCity());
        candidate.setSkills(request.getSkills());
        candidate.setExperienceYears(request.getExperienceYears());
        candidate.setQualification(request.getQualification());
        candidate.setResumeUrl(request.getResumeUrl());
        candidate.setLinkedinUrl(request.getLinkedinUrl());
        candidate.setGithubUrl(request.getGithubUrl());
        candidate.setUpdatedAt(LocalDateTime.now());
        
        candidate=candidateRepository.save(candidate);
        
        return convertToResponse(candidate);
    }

   public void deleteCandidate(Long id) {
    System.out.println("CandidateService.deleteCandidate()");
    Candidate candidate = candidateRepository.findById(id).orElseThrow(() ->
                    new RuntimeException("Candidate not found"));
    candidate.setActive(false);
    candidate.setUpdatedAt(LocalDateTime.now());
    candidateRepository.save(candidate);
}
}

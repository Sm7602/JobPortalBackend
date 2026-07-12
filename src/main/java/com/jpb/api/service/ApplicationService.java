package com.jpb.api.service;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.jpb.api.dao.ApplicationRepository;
import com.jpb.api.dao.CandidateRepository;
import com.jpb.api.dao.JobRepository;
import com.jpb.api.dto.application.ApplicationRequest;
import com.jpb.api.dto.application.ApplicationResponse;
import com.jpb.api.dto.application.ApplicationUpdateRequest;
import com.jpb.api.entity.Application;
import com.jpb.api.entity.Candidate;
import com.jpb.api.entity.Job;

@Service
public class ApplicationService {

    @Autowired
    private ApplicationRepository applicationRepository;

    @Autowired
    private CandidateRepository candidateRepository;

    @Autowired
    private JobRepository jobRepository;
    
    private ApplicationResponse convertToResponse(Application application) {

        return ApplicationResponse.builder()
                .id(application.getId())
                .appliedDate(application.getAppliedDate())
                .status(application.getStatus())
                .coverLetter(application.getCoverLetter())
                .active(application.getActive())
                .createdAt(application.getCreatedAt())
                .updatedAt(application.getUpdatedAt())
                .candidateId(application.getCandidate().getId())
                .candidateName(
                        application.getCandidate().getFirstName()
                        + " "
                        + application.getCandidate().getLastName())
                .jobId(application.getJob().getId())
                .jobTitle(application.getJob().getTitle())
                .build();
    }

    public ApplicationResponse createApplication(ApplicationRequest request){
        System.out.println("ApplicationService.createApplication()");
        Candidate candidate = candidateRepository.findById(request.getCandidateId())
                .orElseThrow(() -> new RuntimeException("Candidate not found"));

        Job job = jobRepository.findById(request.getJobId())
                .orElseThrow(() -> new RuntimeException("Job not found"));

        Application application = new Application();

        application.setCandidate(candidate);
        application.setJob(job);
        application.setCoverLetter(request.getCoverLetter());
        application.setAppliedDate(LocalDate.now());
        application.setStatus("APPLIED");
        application.setCreatedAt(LocalDateTime.now());
        application.setUpdatedAt(LocalDateTime.now());
        application.setActive(true);

        application = applicationRepository.save(application);

        return convertToResponse(application);
    }

    public List<ApplicationResponse> getAllApplications() {
    	 System.out.println("ApplicationService.getAllApplications()");
    	 return applicationRepository.findAll()
    	            .stream()
    	            .map(this::convertToResponse)
    	            .toList();
    }

    public ApplicationResponse getApplicationById(Long id){
    	System.out.println("ApplicationService.getApplicationById()");
    	Application application = applicationRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Application not found"));
        return convertToResponse(application);
    }

    public List<ApplicationResponse> getApplicationsByJob(Long jobId){
    	System.out.println("ApplicationService.getApplicationsByJob()");
        Job job = jobRepository.findById(jobId).orElseThrow(() ->
                        new RuntimeException("Job not found"));
        return applicationRepository.findByJob(job)
        		     .stream()
                 .map(this::convertToResponse)
                 .toList();
    }

    public List<ApplicationResponse> getApplicationsByCandidate(Long candidateId) {
    	System.out.println("ApplicationService.getApplicationsByCandidate()");
        Candidate candidate = candidateRepository.findById(candidateId).orElseThrow(() ->
                        new RuntimeException("Candidate not found"));
        return applicationRepository.findByCandidate(candidate)
                .stream()
                .map(this::convertToResponse)
                .toList();
    }

    public ApplicationResponse updateApplication(
            Long id,
            ApplicationUpdateRequest request) {
    	System.out.println("ApplicationService.updateApplication()");
        Application application = applicationRepository.findById(id).orElseThrow(() ->
                        new RuntimeException("Application not found"));

        application.setStatus(request.getStatus());
        application.setCoverLetter(request.getCoverLetter());
        application.setUpdatedAt(LocalDateTime.now());

        application = applicationRepository.save(application);

        return convertToResponse(application);
    }

    public void deleteApplication(Long id) {
    	System.out.println("ApplicationService.deleteApplication()");
        Application application = applicationRepository.findById(id).orElseThrow(() ->
                        new RuntimeException("Application not found"));
        application.setActive(false);
        application.setUpdatedAt(LocalDateTime.now());
        applicationRepository.save(application);
    }
}
package com.jpb.api.controller;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.jpb.api.dto.application.ApplicationRequest;
import com.jpb.api.dto.application.ApplicationResponse;
import com.jpb.api.dto.application.ApplicationUpdateRequest;
import com.jpb.api.service.ApplicationService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/applications")
public class ApplicationController {

    @Autowired
    private ApplicationService applicationService;


    @PostMapping
    public ApplicationResponse createApplication(
            @Valid @RequestBody ApplicationRequest request) {
        System.out.println("ApplicationController.createApplication()");
        return applicationService.createApplication(request);
    }

    @GetMapping
    public List<ApplicationResponse> getAllApplications(){
        System.out.println("ApplicationController.getAllApplications()");
        return applicationService.getAllApplications();
    }

    @GetMapping("/{id}")
    public ApplicationResponse getApplicationById(@PathVariable Long id) {
        System.out.println("ApplicationController.getAllApplications()");
        return applicationService.getApplicationById(id);
    }

    @GetMapping("/job/{jobId}")
    public List<ApplicationResponse> getApplicationsByJob( @PathVariable Long jobId) {
        System.out.println("ApplicationController.getApplicationsByJob()");
        return applicationService.getApplicationsByJob(jobId);
    }

    @GetMapping("/candidate/{candidateId}")
    public List<ApplicationResponse> getApplicationsByCandidate(@PathVariable Long candidateId) {
        System.out.println("ApplicationController.getApplicationsByCandidate()");
        return applicationService.getApplicationsByCandidate(candidateId);
    }

    @PutMapping("/{id}")
    public ApplicationResponse updateApplication(
            @PathVariable Long id,
            @Valid @RequestBody ApplicationUpdateRequest request){
    	  System.out.println("ApplicationController.updateApplication()");
        return applicationService.updateApplication(id,request);
    }

    @DeleteMapping("/{id}")
    public String deleteApplication(@PathVariable Long id) {
    	System.out.println("ApplicationController.deleteApplication()");
        applicationService.deleteApplication(id);
        return "Application deleted successfully";
    }
}

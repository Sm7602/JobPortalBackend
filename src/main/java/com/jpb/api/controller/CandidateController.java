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
import com.jpb.api.dto.candidate.CandidateRequest;
import com.jpb.api.dto.candidate.CandidateResponse;
import com.jpb.api.dto.candidate.CandidateUpdateRequest;
import com.jpb.api.service.CandidateService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/candidates")
public class CandidateController {

    @Autowired
    private CandidateService candidateService;

    @PostMapping
    public CandidateResponse  saveCandidate( @Valid @RequestBody CandidateRequest request) {
        System.out.println("CandidateController.saveCandidate()");
        return candidateService.saveCandidate(request);
    }

    @GetMapping
    public List<CandidateResponse> getAllCandidates() {
    	System.out.println("CandidateController.getAllCandidates()");
        return candidateService.getAllCandidates();
    }

    @GetMapping("/{id}")
    public CandidateResponse getCandidateById(@PathVariable Long id) {
    	System.out.println("CandidateController.getCandidateById()");
        return candidateService.getCandidateById(id);
    }

    @PutMapping("/{id}")
    public CandidateResponse updateCandidate(@PathVariable Long id,@Valid @RequestBody CandidateUpdateRequest request) {
    	System.out.println("CandidateController.updateCandidate()");
        return candidateService.updateCandidate(id, request);
    }

    @DeleteMapping("/{id}")
    public String deleteCandidate(@PathVariable Long id) {
    	System.out.println("CandidateController.deleteCandidate()");
        candidateService.deleteCandidate(id);
        return "Candidate deleted successfully";
    }
}
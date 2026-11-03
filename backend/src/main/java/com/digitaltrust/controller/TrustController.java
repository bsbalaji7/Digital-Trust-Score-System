package com.digitaltrust.controller;

import com.digitaltrust.dto.TrustRequest;
import com.digitaltrust.dto.TrustResponse;
import com.digitaltrust.service.TrustScoreService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/trust")
public class TrustController {

    private final TrustScoreService trustScoreService;

    @Autowired
    public TrustController(TrustScoreService trustScoreService) {
        this.trustScoreService = trustScoreService;
    }

    @PostMapping("/calculate")
    public ResponseEntity<TrustResponse> calculateTrustScore(@Valid @RequestBody TrustRequest request) {
        TrustResponse response = trustScoreService.calculateAndSave(request);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/methodology")
    public ResponseEntity<Map<String, Object>> getMethodology() {
        return ResponseEntity.ok(trustScoreService.getMethodology());
    }
}

package com.example.loan.controller;

import com.example.loan.dto.LoanApplicationRequest;
import com.example.loan.dto.LoanApplicationResponse;
import com.example.loan.dto.RepaymentResponse;
import com.example.loan.entity.LoanApplication;
import com.example.loan.service.LoanApplicationService;
import com.example.loan.service.RepaymentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customer/loans")
@RequiredArgsConstructor
public class LoanApplicationController {

    private final LoanApplicationService loanService;
    private final RepaymentService repaymentService;
    @PostMapping("/apply")
    public ResponseEntity<String> applyLoan(@Valid @RequestBody LoanApplicationRequest request,
                                            Authentication authentication) {
        String email = authentication.getName();
        String response = loanService.applyLoan(email, request);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/my")
    public ResponseEntity<List<LoanApplicationResponse>> getCustomerLoans(Authentication authentication) {
        String email = authentication.getName();
        List<LoanApplicationResponse> loans = loanService.getCustomerLoans(email);
        return ResponseEntity.ok(loans);
    }
    @GetMapping("/repayments/loan/{loanId}")
    public ResponseEntity<List<RepaymentResponse>> getRepaymentsByLoan(@PathVariable Long loanId, Authentication authentication) {
        String email = authentication.getName();
        List<RepaymentResponse> repayments = repaymentService.getCustomerRepaymentHistory(loanId, email);
        return ResponseEntity.ok(repayments);
    }







}

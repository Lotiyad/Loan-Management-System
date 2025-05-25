package com.example.loan.controller;

import com.example.loan.dto.LoanApplicationResponse;
import com.example.loan.service.LoanApplicationService;
import com.example.loan.service.LoanApprovalService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/loans")
@RequiredArgsConstructor
public class AdminLoanController {

    private final LoanApprovalService loanService;

    @GetMapping("/pending")
    public ResponseEntity<List<LoanApplicationResponse>> getPendingLoans() {
        return ResponseEntity.ok(loanService.getPendingLoans());
    }

    @PutMapping("/approve/{loanId}")
    public ResponseEntity<String> approveLoan(
            @PathVariable Long loanId,
            @RequestParam String remarks) {
        return ResponseEntity.ok(loanService.approveLoan(loanId, remarks));
    }

    @PutMapping("/reject/{loanId}")
    public ResponseEntity<String> rejectLoan(
            @PathVariable Long loanId,
            @RequestParam String remarks) {
        return ResponseEntity.ok(loanService.rejectLoan(loanId, remarks));
    }

}


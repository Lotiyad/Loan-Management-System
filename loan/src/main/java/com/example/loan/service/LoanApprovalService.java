package com.example.loan.service;

import com.example.loan.dto.LoanApplicationResponse;
import com.example.loan.dto.RepaymentResponse;
import com.example.loan.entity.LoanApplication;
import com.example.loan.entity.Repayment;
import com.example.loan.mapper.LoanApplicationMapper;
import com.example.loan.repository.LoanApplicationRepository;
import com.example.loan.repository.RepaymentRepository;
import com.example.loan.user.LoanStatus;
import com.example.loan.user.UserRepository;
import com.example.loan.util.EmiCalculator;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class LoanApprovalService {

    private final LoanApplicationRepository loanRepository;
    private final RepaymentRepository repaymentRepository;
    private final UserRepository userRepository;

    public List<LoanApplicationResponse> getPendingLoans() {
        return loanRepository.findByStatus(LoanStatus.PENDING).stream()
                .map(LoanApplicationMapper::toDto)
                .collect(Collectors.toList());
    }


    public String approveLoan(Long loanId, String remarks) {
        LoanApplication loan = loanRepository.findById(loanId)
                .orElseThrow(() -> new RuntimeException("Loan not found"));

        if (loan.getStatus() != LoanStatus.PENDING) {
            return "Loan already processed.";
        }

        double P = loan.getLoanAmount();
        int N = loan.getDurationMonths();
        double annualRate = 0.12;
        double emi = EmiCalculator.calculateEMI(P, N, annualRate);

        loan.setStatus(LoanStatus.APPROVED);
        loan.setRemarks(remarks);
        loan.setApprovalDate(LocalDate.now());
        loan.setStartDate(LocalDate.now());
        loan.setEmiAmount(emi);
        loanRepository.save(loan);


        for (int i = 1; i <= N; i++) {
            Repayment repayment = new Repayment();
            repayment.setLoan(loan);
            repayment.setAmount(emi);
            repayment.setDueDate(LocalDate.now().plusMonths(i));
            repayment.setPaid(false);
            repaymentRepository.save(repayment);
        }

        return String.format("Loan approved. EMI: %.2f", emi);
    }


    public String rejectLoan(Long loanId, String remarks) {
        LoanApplication loan = loanRepository.findById(loanId)
                .orElseThrow(() -> new RuntimeException("Loan not found"));

        if (loan.getStatus() != LoanStatus.PENDING) {
            return "Loan already processed.";
        }

        loan.setStatus(LoanStatus.REJECTED);
        loan.setRemarks(remarks);
        loan.setApprovalDate(LocalDate.now());
        loanRepository.save(loan);

        return "Loan rejected.";
    }



}

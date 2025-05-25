package com.example.loan.service;

import com.example.loan.dto.LoanApplicationRequest;
import com.example.loan.dto.LoanApplicationResponse;
import com.example.loan.entity.LoanApplication;
import com.example.loan.mapper.LoanApplicationMapper;
import com.example.loan.repository.LoanApplicationRepository;
import com.example.loan.user.User;
import com.example.loan.user.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class LoanApplicationService {

    private final LoanApplicationRepository loanRepository;
    private final UserRepository userRepository;

    public String applyLoan(String email, LoanApplicationRequest request) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        LoanApplication loan = LoanApplicationMapper.toEntity(request, user);

        loanRepository.save(loan);
        return "Loan application submitted successfully with status PENDING";
    }

    public List<LoanApplicationResponse> getCustomerLoans(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        List<LoanApplication> loans = loanRepository.findByCustomerEmail(email);

        return loans.stream()
                .map(LoanApplicationMapper::toDto)
                .collect(Collectors.toList());
    }
}

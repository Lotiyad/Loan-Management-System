package com.example.loan.service;

import com.example.loan.dto.RepaymentResponse;
import com.example.loan.entity.LoanApplication;
import com.example.loan.entity.Repayment;
import com.example.loan.repository.LoanApplicationRepository;
import com.example.loan.repository.RepaymentRepository;
import com.example.loan.user.LoanStatus;
import com.example.loan.user.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class RepaymentService {

    private final RepaymentRepository repaymentRepository;
    private final LoanApplicationRepository loanRepository;
    private final UserRepository userRepository;

    public String markRepaymentAsPaid(Long repaymentId) {
        Repayment repayment = repaymentRepository.findById(repaymentId)
                .orElseThrow(() -> new RuntimeException("Repayment not found"));

        if (Boolean.TRUE.equals(repayment.getPaid())) {
            return "This repayment is already paid.";
        }

        Long currentUserId = getCurrentUserId();
        if (!repayment.getLoan().getCustomer().getId().equals(currentUserId)) {
            throw new RuntimeException("Unauthorized repayment access.");
        }

        repayment.setPaid(true);
        repaymentRepository.save(repayment);

        updateLoanStatusIfCompleted(repayment.getLoan().getId());

        return "Marked as paid.";
    }

    private Long getCurrentUserId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();

        return userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"))
                .getId();
    }

    public void updateLoanStatusIfCompleted(Long loanId) {
        LoanApplication loan = loanRepository.findById(loanId)
                .orElseThrow(() -> new RuntimeException("Loan not found"));

        boolean allPaid = loan.getRepayments().stream().allMatch(Repayment::getPaid);
        if (allPaid && loan.getStatus() == LoanStatus.APPROVED) {
            loan.setStatus(LoanStatus.COMPLETED);
            loanRepository.save(loan);
        }
    }

    public List<Repayment> getAllRepayments() {
        return repaymentRepository.findAll();
    }


    public List<RepaymentResponse> getCustomerRepaymentHistory(Long loanId, String email) {
        LoanApplication loan = loanRepository.findById(loanId)
                .orElseThrow(() -> new RuntimeException("Loan not found"));

        // Ensure only the owner can view
        if (!loan.getCustomer().getEmail().equals(email)) {
            throw new RuntimeException("Unauthorized access to repayment history.");
        }

        return loan.getRepayments().stream()
                .map(r -> new RepaymentResponse(r.getId(), r.getDueDate(), r.getAmount(), r.getPaid()))
                .collect(Collectors.toList());
    }

}


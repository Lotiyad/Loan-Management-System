package com.example.loan.mapper;

import com.example.loan.dto.LoanApplicationRequest;
import com.example.loan.dto.LoanApplicationResponse;
import com.example.loan.entity.LoanApplication;
import com.example.loan.user.LoanStatus;
import com.example.loan.user.User;

public class LoanApplicationMapper {

    public static LoanApplicationResponse toDto(LoanApplication loan) {
        if (loan == null) return null;
        return LoanApplicationResponse.builder()
                .id(loan.getId())
                .loanAmount(loan.getLoanAmount())
                .loanType(loan.getLoanType())
                .durationMonths(loan.getDurationMonths())
                .purpose(loan.getPurpose())
                .annualIncome(loan.getAnnualIncome())
                .status(loan.getStatus().name())
                .build();
    }

    public static LoanApplication toEntity(LoanApplicationRequest request, User user) {
        if (request == null) return null;
        return LoanApplication.builder()
                .loanAmount(request.getLoanAmount())
                .loanType(request.getLoanType())
                .durationMonths(request.getDurationMonths())
                .purpose(request.getPurpose())
                .annualIncome(request.getAnnualIncome())
                .status(LoanStatus.PENDING)
                .customer(user)
                .build();
    }
}


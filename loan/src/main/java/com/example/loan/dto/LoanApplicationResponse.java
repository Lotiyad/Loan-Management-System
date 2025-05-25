package com.example.loan.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LoanApplicationResponse {
    private Long id;
    private Double loanAmount;
    private String loanType;
    private Integer durationMonths;
    private String purpose;
    private Double annualIncome;
    private String status;
}


package com.example.loan.dto;



import jakarta.validation.constraints.*;

import lombok.Data;

@Data
public class LoanApplicationRequest {

    @NotNull(message = "Loan amount is required")
    @Positive(message = "Loan amount must be positive")
    private Double loanAmount;

    @NotBlank(message = "Loan type is required")
    private String loanType;

    @NotNull(message = "Duration is required")
    @Min(value = 1, message = "Duration must be at least 1 month")
    private Integer durationMonths;

    @NotBlank(message = "Purpose is required")
    private String purpose;

    @NotNull(message = "Annual income is required")
    @Positive(message = "Annual income must be positive")
    private Double annualIncome;
}


package com.example.loan.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import java.time.LocalDate;

@Data
@AllArgsConstructor
public class RepaymentResponse {
    private Long id;
    private LocalDate dueDate;
    private Double amount;
    private Boolean paid;
}




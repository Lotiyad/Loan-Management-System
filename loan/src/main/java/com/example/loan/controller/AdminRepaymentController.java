package com.example.loan.controller;

import com.example.loan.dto.RepaymentResponse;
import com.example.loan.entity.Repayment;

import com.example.loan.service.RepaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/admin/repayments")
@RequiredArgsConstructor
public class AdminRepaymentController {

    private final RepaymentService repaymentService;


    @GetMapping("/all")
    public ResponseEntity<List<RepaymentResponse>> getAllRepayments() {
        List<Repayment> repayments = repaymentService.getAllRepayments();

        List<RepaymentResponse> responses = repayments.stream()
                .map(r -> new RepaymentResponse(r.getId(), r.getDueDate(), r.getAmount(), r.getPaid()))
                .collect(Collectors.toList());
        return ResponseEntity.ok(responses);
    }

}


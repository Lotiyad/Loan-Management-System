package com.example.loan.controller;



import com.example.loan.service.RepaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/repayments")
@RequiredArgsConstructor
public class RepaymentController {

    private final RepaymentService repaymentService;


    @PostMapping("/{id}/pay")
    public ResponseEntity<String> markRepaymentAsPaid(@PathVariable Long id) {
        String message = repaymentService.markRepaymentAsPaid(id);
        return ResponseEntity.ok(message);
    }
}



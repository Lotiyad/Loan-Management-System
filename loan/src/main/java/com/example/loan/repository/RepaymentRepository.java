package com.example.loan.repository;

import com.example.loan.entity.LoanApplication;
import com.example.loan.entity.Repayment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RepaymentRepository extends JpaRepository<Repayment, Long> {
    List<Repayment> findByLoan(LoanApplication loan);
}


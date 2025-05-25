package com.example.loan.repository;


import com.example.loan.entity.LoanApplication;
import com.example.loan.user.LoanStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LoanApplicationRepository extends JpaRepository<LoanApplication, Long> {


    @Query("SELECT l FROM LoanApplication l WHERE l.customer.email = :email")
    List<LoanApplication> findByCustomerEmail(@Param("email") String email);
    List<LoanApplication> findByStatus(LoanStatus status);




}



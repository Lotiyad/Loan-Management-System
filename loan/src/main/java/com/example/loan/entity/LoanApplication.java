package com.example.loan.entity;



import com.example.loan.user.LoanStatus;
import com.example.loan.user.User;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "customer")
public class LoanApplication {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double loanAmount;

    private String loanType;

    private Integer durationMonths;

    private String purpose;

    private Double annualIncome;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private LoanStatus status;

    @Column
    private LocalDate approvalDate;

    @Column
    private Double emiAmount;

    @Column
    private String remarks;

    @Column
    private LocalDate startDate;
    @OneToMany(mappedBy = "loan", cascade = CascadeType.ALL)
    private List<Repayment> repayments;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id",nullable = false)
    private User customer;

}


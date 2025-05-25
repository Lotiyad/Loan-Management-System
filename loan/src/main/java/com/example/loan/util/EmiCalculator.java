package com.example.loan.util;


public class EmiCalculator {

    public static double calculateEMI(double principal, int months, double annualRate) {
        double R = (annualRate / 100) / 12; // Convert annual rate (%) to monthly rate (decimal)
        return (principal * R * Math.pow(1 + R, months)) /
                (Math.pow(1 + R, months) - 1);
    }
}



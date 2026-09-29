package com.reva.payroll.model;

public interface Payable {
    double calculateNetSalary();

    default void printPaymentStatus() {
        System.out.println("Payment status: Salary calculated successfully.");
    }
}

package com.reva.payroll.service;

import com.reva.payroll.model.Employee;
import com.reva.payroll.model.Payable;

public class PayrollService {

    public void printAllEmployees(EmployeeManager manager) {
        if (manager.getCount() == 0) {
            System.out.println("No employees found.");
            return;
        }

        System.out.println("\nID     Name                 Department      Designation           Basic");
        System.out.println("--------------------------------------------------------------------------");
        for (int i = 0; i < manager.getCount(); i++) {
            System.out.println(manager.getEmployees()[i]);
        }
    }

    public void generatePayslip(Employee employee) {
        if (employee == null) {
            System.out.println("Employee not found.");
            return;
        }

        double gross = employee.calculateGrossSalary();
        double net = employee.calculateNetSalary();
        double deductions = gross - net;

        // Explicit type conversion/casting example.
        int roundedNetSalary = (int) net;

        System.out.println("\n==============================================");
        System.out.println("                 PAYSLIP");
        System.out.println("==============================================");
        System.out.println("Employee ID   : " + employee.getEmployeeId());
        System.out.println("Name          : " + employee.getName());
        System.out.println("Department    : " + employee.getDepartment());
        System.out.println("Designation   : " + employee.getDesignation());
        System.out.println("Employee Type : " + employee.getEmployeeType());
        System.out.printf("Basic Salary  : Rs. %.2f%n", employee.getBasicSalary());
        System.out.printf("Gross Salary  : Rs. %.2f%n", gross);
        System.out.printf("Deductions    : Rs. %.2f%n", deductions);
        System.out.printf("Net Salary    : Rs. %.2f%n", net);
        System.out.println("Rounded Net   : Rs. " + roundedNetSalary);
        System.out.println("==============================================");

        Payable payable = (Payable) employee;
        payable.printPaymentStatus();
        employee.showCompanyPolicy();
    }

    public void calculateSalary(Employee employee) {
        if (employee == null) {
            System.out.println("Employee not found.");
            return;
        }
        System.out.printf("Net salary of %s: Rs. %.2f%n",
                employee.getName(), employee.calculateNetSalary());
    }
}

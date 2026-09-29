package com.reva.payroll.model;

public class PermanentEmployee extends Employee implements Payable {

    public PermanentEmployee() {
        super();
    }

    public PermanentEmployee(int employeeId, String name, Department department,
                             String designation, double basicSalary) {
        super(employeeId, name, department, designation, basicSalary);
    }

    @Override
    public double calculateNetSalary() {
        double gross = calculateGrossSalary(0.20, 0.10);
        double pf = getBasicSalary() * PF_RATE;
        double tax = gross > 50000 ? gross * 0.10 : 0.0;
        return gross - pf - tax;
    }

    @Override
    public String getEmployeeType() {
        return "Permanent";
    }
}

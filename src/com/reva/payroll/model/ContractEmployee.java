package com.reva.payroll.model;

public class ContractEmployee extends Employee implements Payable {

    public ContractEmployee() {
        super();
    }

    public ContractEmployee(int employeeId, String name, Department department,
                            String designation, double basicSalary) {
        super(employeeId, name, department, designation, basicSalary);
    }

    @Override
    public double calculateNetSalary() {
        double gross = calculateGrossSalary(0.15, 0.05);
        double professionalTax = 200.0;
        return gross - professionalTax;
    }

    @Override
    public String getEmployeeType() {
        return "Contract";
    }
}

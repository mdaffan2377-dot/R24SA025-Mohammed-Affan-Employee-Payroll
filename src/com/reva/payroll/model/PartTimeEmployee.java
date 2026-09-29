package com.reva.payroll.model;

public class PartTimeEmployee extends Employee implements Payable {

    private int hoursWorked;

    public PartTimeEmployee() {
        this(0, "Unknown", Department.HR, "Part-Time", 0.0, 0);
    }

    public PartTimeEmployee(int employeeId, String name, Department department,
                            String designation, double hourlyRate, int hoursWorked) {
        super(employeeId, name, department, designation, hourlyRate);
        this.hoursWorked = hoursWorked;
    }

    public int getHoursWorked() { return hoursWorked; }
    public void setHoursWorked(int hoursWorked) { this.hoursWorked = hoursWorked; }

    @Override
    public double calculateNetSalary() {
        double gross = getBasicSalary() * hoursWorked;
        double deduction = gross * 0.05;
        return gross - deduction;
    }

    @Override
    public String getEmployeeType() {
        return "Part-Time";
    }
}

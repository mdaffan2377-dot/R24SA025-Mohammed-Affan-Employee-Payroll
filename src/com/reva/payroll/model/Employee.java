package com.reva.payroll.model;

public abstract class Employee {
    private int employeeId;
    private String name;
    private Department department;
    private String designation;
    private double basicSalary;

    private static int totalEmployees = 0;
    public static final double PF_RATE = 0.12;

    public Employee() {
        this(0, "Unknown", Department.HR, "Trainee", 0.0);
    }

    public Employee(int employeeId, String name, Department department,
                    String designation, double basicSalary) {
        this.employeeId = employeeId;
        this.name = name;
        this.department = department;
        this.designation = designation;
        this.basicSalary = basicSalary;
        totalEmployees++;
    }

    public int getEmployeeId() { return employeeId; }
    public void setEmployeeId(int employeeId) { this.employeeId = employeeId; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public Department getDepartment() { return department; }
    public void setDepartment(Department department) { this.department = department; }

    public String getDesignation() { return designation; }
    public void setDesignation(String designation) { this.designation = designation; }

    public double getBasicSalary() { return basicSalary; }
    public void setBasicSalary(double basicSalary) { this.basicSalary = basicSalary; }

    public static int getTotalEmployees() {
        return totalEmployees;
    }

    protected double calculateGrossSalary(double hraRate, double daRate) {
        // Operator precedence: multiplication is evaluated before addition.
        return basicSalary + basicSalary * hraRate + basicSalary * daRate;
    }

    public double calculateGrossSalary() {
        return calculateGrossSalary(0.20, 0.10);
    }

    public abstract double calculateNetSalary();

    public abstract String getEmployeeType();

    public final void showCompanyPolicy() {
        // Final method: payroll policy should not be changed by subclasses.
        System.out.println("Payroll policy: PF is calculated at " + (PF_RATE * 100) + "%.");
    }

    @Override
    public String toString() {
        return String.format("%-6d %-20s %-15s %-18s %10.2f",
                employeeId, name, department, designation, basicSalary);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Employee)) return false;
        Employee other = (Employee) obj;
        return employeeId == other.employeeId;
    }
}

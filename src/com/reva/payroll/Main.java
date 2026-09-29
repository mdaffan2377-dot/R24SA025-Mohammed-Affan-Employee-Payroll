
package com.reva.payroll;

import com.reva.payroll.model.*;
import com.reva.payroll.service.EmployeeManager;
import com.reva.payroll.service.PayrollService;
import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static final EmployeeManager manager = new EmployeeManager();
    private static final PayrollService payrollService = new PayrollService();

    public static void main(String[] args) {
        loadSampleEmployees();

        int choice;
        do {
            printMenu();
            choice = readInt("Enter your choice: ");

            switch (choice) {
                case 1:
                    addEmployee();
                    break;
                case 2:
                    payrollService.printAllEmployees(manager);
                    break;
                case 3:
                    searchEmployee();
                    break;
                case 4:
                    updateEmployee();
                    break;
                case 5:
                    deleteEmployee();
                    break;
                case 6:
                    calculateSalary();
                    break;
                case 7:
                    generatePayslip();
                    break;
                case 8:
                    System.out.println("Thank you for using Employee Payroll Management System.");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 8);

        scanner.close();
    }

    private static void printMenu() {
        System.out.println("\n========================================");
        System.out.println("     EMPLOYEE PAYROLL MANAGEMENT");
        System.out.println("========================================");
        System.out.println("1. Add Employee");
        System.out.println("2. View All Employees");
        System.out.println("3. Search Employee");
        System.out.println("4. Update Employee");
        System.out.println("5. Delete Employee");
        System.out.println("6. Calculate Salary");
        System.out.println("7. Generate Payslip");
        System.out.println("8. Exit");
        System.out.println("Total objects created: " + Employee.getTotalEmployees());
    }

    private static void loadSampleEmployees() {
        manager.addEmployee(new PermanentEmployee(
                101, "Rahul", Department.IT, "Software Engineer", 45000));

        manager.addEmployee(new ContractEmployee(
                102, "Priya", Department.HR, "HR Executive", 35000));

        manager.addEmployee(new PartTimeEmployee(
                103, "Arjun", Department.BIOINFORMATICS, "Lab Assistant", 500, 80));
    }

    private static void addEmployee() {
        System.out.println("\n1. Permanent");
        System.out.println("2. Contract");
        System.out.println("3. Part-Time");
        int type = readInt("Select employee type: ");

        int id = readInt("Employee ID: ");
        String name = readLine("Name: ");
        Department department = readDepartment();
        String designation = readLine("Designation: ");

        Employee employee;

        if (type == 1) {
            double salary = readDouble("Basic salary: ");
            employee = new PermanentEmployee(id, name, department, designation, salary);
        } else if (type == 2) {
            double salary = readDouble("Basic salary: ");
            employee = new ContractEmployee(id, name, department, designation, salary);
        } else if (type == 3) {
            double hourlyRate = readDouble("Hourly rate: ");
            int hours = readInt("Hours worked: ");
            employee = new PartTimeEmployee(id, name, department, designation,
                    hourlyRate, hours);
        } else {
            System.out.println("Invalid employee type.");
            return;
        }

        if (manager.findEmployee(id) != null) {
            System.out.println("Employee ID already exists.");
            return;
        }

        if (manager.addEmployee(employee)) {
            System.out.println("Employee added successfully.");
        } else {
            System.out.println("Unable to add employee.");
        }
    }

    private static void searchEmployee() {
        System.out.println("\n1. Search by ID");
        System.out.println("2. Search by Name");
        int choice = readInt("Enter choice: ");

        Employee employee = null;

        if (choice == 1) {
            employee = manager.findEmployee(readInt("Enter employee ID: "));
        } else if (choice == 2) {
            employee = manager.findEmployee(readLine("Enter employee name: "));
        } else {
            System.out.println("Invalid choice.");
            return;
        }

        if (employee != null) {
            System.out.println("\nEmployee found:");
            System.out.println(employee);
        } else {
            System.out.println("Employee not found.");
        }
    }

    private static void updateEmployee() {
        int id = readInt("Enter employee ID to update: ");
        Employee employee = manager.findEmployee(id);

        if (employee == null) {
            System.out.println("Employee not found.");
            return;
        }

        String designation = readLine("New designation: ").trim();
        double salary = readDouble("New basic salary/hourly rate: ");

        if (!designation.isEmpty()) {
            employee.setDesignation(designation);
        }
        employee.setBasicSalary(salary);

        if (employee instanceof PartTimeEmployee) {
            int hours = readInt("New hours worked: ");
            ((PartTimeEmployee) employee).setHoursWorked(hours);
        }

        System.out.println("Employee updated successfully.");
    }

    private static void deleteEmployee() {
        int id = readInt("Enter employee ID to delete: ");

        if (manager.deleteEmployee(id)) {
            System.out.println("Employee deleted successfully.");
        } else {
            System.out.println("Employee not found.");
        }
    }

    private static void calculateSalary() {
        int id = readInt("Enter employee ID: ");
        payrollService.calculateSalary(manager.findEmployee(id));
    }

    private static void generatePayslip() {
        int id = readInt("Enter employee ID: ");
        payrollService.generatePayslip(manager.findEmployee(id));
    }

    private static Department readDepartment() {
        System.out.println("Departments: HR, IT, FINANCE, SALES, BIOINFORMATICS");
        while (true) {
            String input = readLine("Department: ").trim().toUpperCase();
            try {
                return Department.valueOf(input);
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid department. Please enter a listed department.");
            }
        }
    }

    private static int readInt(String message) {
        while (true) {
            try {
                System.out.print(message);
                return Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid integer.");
            }
        }
    }

    private static double readDouble(String message) {
        while (true) {
            try {
                System.out.print(message);
                return Double.parseDouble(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
            }
        }
    }

    private static String readLine(String message) {
        System.out.print(message);
        return scanner.nextLine();
    }
}

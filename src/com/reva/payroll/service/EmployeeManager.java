package com.reva.payroll.service;

import com.reva.payroll.model.Employee;

public class EmployeeManager {
    private Employee[] employees;
    private int count;

    public EmployeeManager() {
        employees = new Employee[50];
        count = 0;
    }

    public boolean addEmployee(Employee employee) {
        if (employee == null || count >= employees.length) {
            return false;
        }
        employees[count++] = employee;
        return true;
    }

    public Employee findEmployee(int id) {
        for (int i = 0; i < count; i++) {
            if (employees[i].getEmployeeId() == id) {
                return employees[i];
            }
        }
        return null;
    }

    public Employee findEmployee(String name) {
        String searchName = name.trim();
        for (int i = 0; i < count; i++) {
            if (employees[i].getName().equalsIgnoreCase(searchName)) {
                return employees[i];
            }
        }
        return null;
    }

    public boolean deleteEmployee(int id) {
        for (int i = 0; i < count; i++) {
            if (employees[i].getEmployeeId() == id) {
                for (int j = i; j < count - 1; j++) {
                    employees[j] = employees[j + 1];
                }
                employees[--count] = null;
                return true;
            }
        }
        return false;
    }

    public int getCount() {
        return count;
    }

    public Employee[] getEmployees() {
        return employees;
    }
}

package com.example.homework2_5.service;

import com.example.homework2_5.exception.EmployeeAlreadyAddedException;
import com.example.homework2_5.exception.EmployeeNotFoundException;
import com.example.homework2_5.exception.EmployeeStorageFullException;
import com.example.homework2_5.model.Employee;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService {
    private static final int SIZE = 6;
    private final Employee[] employees;

    public EmployeeService() {
        this.employees = new Employee[SIZE];
    }

    public Employee add(String firstName, String lastName) {
        Employee employee = new Employee(firstName, lastName);
        int index = -1;
        for (int i = 0; i < employees.length; i++) {
            if (employees[i] == null && index == -1) {
                index = 1;
            }
            if (employee.equals(employees[i])) {
                throw new EmployeeAlreadyAddedException();
            }
        }
        if (index == -1) {
            throw new EmployeeStorageFullException();
        }
        employees[index] = employee;
        return employee;
    }

    public Employee remove(String firstName, String lastName) {
        Employee employee = new Employee(firstName, lastName);

        for (int i = 0; i < employees.length; i++) {

            if (employee.equals(employees[i])) {
                employees[i] = null;
                return employee;
            }
        }
        throw new EmployeeNotFoundException();
    }

    public Employee find(String firstName, String lastName) {
        Employee employee = new Employee(firstName, lastName);

        for (Employee i : employees) {
            if (employee.equals(i)) {
                return employee;
            }
        }
        throw new EmployeeNotFoundException();
    }
}

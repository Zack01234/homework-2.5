package com.example.homework2_5.service;

import com.example.homework2_5.exception.EmployeeAlreadyAddedException;
import com.example.homework2_5.exception.EmployeeNotFoundException;
import com.example.homework2_5.exception.EmployeeStorageFullException;
import com.example.homework2_5.model.Employee;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeeService {
    private static final int SIZE = 6;
    private final List<Employee> employees;

    public EmployeeService() {
        this.employees = new ArrayList<>();
    }

    public Employee add(String firstName, String lastName) {
        Employee employee = new Employee(firstName, lastName);
        if (employees.contains(employee)) {
            throw new EmployeeAlreadyAddedException();
        }
        if (employees.size() >= SIZE) {
            throw new EmployeeAlreadyAddedException();
        }
        employees.add(employee);
        return employee;
    }

    public Employee remove(String firstName, String lastName) {
        Employee employee = new Employee(firstName, lastName);
        if (employees.contains(employee)) {
            employees.remove(employee);
            return employee;
        }
        throw new EmployeeNotFoundException();
    }

    public Employee find(String firstName, String lastName) {
        Employee employee = new Employee(firstName, lastName);
        if (employees.contains(employee)) {
            return employee;
        }
        throw new EmployeeNotFoundException();
    }

    public List<Employee> findAll() {
        return List.of(employees.toArray(new Employee[0]));
    }
}

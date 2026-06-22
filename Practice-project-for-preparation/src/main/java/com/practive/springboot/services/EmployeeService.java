package com.practive.springboot.services;

import com.practive.springboot.entities.Employee;

import java.util.List;
import java.util.Optional;


public interface EmployeeService {

    public List<Employee> getAllEmployees();

    Employee saveEmployee(Employee employee);

    Employee updateEmployee(Long id, Employee employee);

    void deleteEmployee(Long id);

    Optional<Employee> getEmployeeById(Long id);

    void deleteByEmailId(String email);
}

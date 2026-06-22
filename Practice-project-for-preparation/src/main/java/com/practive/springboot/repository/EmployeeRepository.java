package com.practive.springboot.repository;

import com.practive.springboot.entities.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    public Optional<Employee> findByEmail(String email);

    public void deleteByEmail(String email);
}

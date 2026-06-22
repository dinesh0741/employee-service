package com.practive.springboot.serviceImpl;

import com.practive.springboot.entities.Employee;
import com.practive.springboot.exceptions.DuplicateResourceException;
import com.practive.springboot.exceptions.ResourceNotFoundException;
import com.practive.springboot.repository.EmployeeRepository;
import com.practive.springboot.services.EmployeeService;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;

    EmployeeServiceImpl(EmployeeRepository employeeRepository){
        log.info("EmployeeServiceImpl : Injecting EmployeeRepo Dependency");
        this.employeeRepository = employeeRepository;
    }

    @Override
    public List<Employee> getAllEmployees() {
        log.info("EmployeeServiceImpl : Returning all the employees ");
        return employeeRepository.findAll();
    }

    @Override
    public Optional<Employee> getEmployeeById(Long id) {
        log.info("EmployeeServiceImpl: Finding employee by ID : {}", id );
        Optional<Employee> employee = employeeRepository.findById(id);
        if(employee.isEmpty()){
            log.error("EmployeeServiceImpl : Employee not found with ID : {}", id);
            throw new ResourceNotFoundException("Employee not found with Id : " + id);
        }
        log.info("EmployeeServiceImpl: Employee found : {} " , employee.toString());
        return employeeRepository.findById(id);
    }

    @Override
    public Employee saveEmployee(Employee employee) {
        log.info("EmployeeServiceImpl : Saving the employee with Email : {} ", employee.getEmail());
        Optional<Employee> existing = employeeRepository.findByEmail(employee.getEmail());
        if(existing.isPresent()){
            log.error("Employee Already Exist with this email : {}", employee.getEmail() );
            throw new DuplicateResourceException("Employee Already exist with provided email");
        }
        return employeeRepository.save(employee);
    }

    @Override
    public Employee updateEmployee(Long id, Employee employee) {
        Optional<Employee> existing = this.getEmployeeById(id);
        if(existing.isEmpty()){
            log.error("EmployeeServiceImpl : Employee not found with ID : {}", id);
            throw new ResourceNotFoundException("Employee not found with Id : " + id);
        }
        log.info("Updating the employee for the emailID : {} ", employee.getEmail());
        Employee employee1 = Employee.builder()
                .id(id)
                .name(employee.getName())
                .email(employee.getEmail())
                .build();
        return employeeRepository.save(employee1);

    }

    @Override
    public void deleteEmployee(Long id) {
        Optional<Employee> existing = this.getEmployeeById(id);
        if(existing.isEmpty()){
            log.error("EmployeeServiceImpl : Employee not found with ID : {}", id);
            throw new ResourceNotFoundException("Employee not found with Id : " + id);
        }
        log.info("Employee deleted");
        employeeRepository.deleteById(id);
    }

    @Transactional
    public void deleteByEmailId(String email){
        Optional<Employee> employee = employeeRepository.findByEmail(email);
        if(employee.isPresent()){
            employeeRepository.deleteByEmail(email);
        }
    }

}

package com.sgic.jpa.controller;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.Size;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.sgic.jpa.model.Employee;
import com.sgic.jpa.repository.EmployeeRepository;


@RestController
public class EmployeeController {

	@Autowired
    private EmployeeRepository employeeRepository;
    
	
    
    @GetMapping("/employees")
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }
    
    @PostMapping("/employees")
    public Employee createEmployee(@Valid @RequestBody Employee employee) {
        return employeeRepository.save(employee);
    }
    
    
    @PutMapping("/employees/{employeeId}")
    public Employee updateEmployee(@PathVariable Long employeeId, @Valid @RequestBody Employee employeeRequest) {
        return employeeRepository.findById(employeeId).map(employee -> {
        	employee.setFirstName(employeeRequest.getFirstName());
        	employee.setLastName(employeeRequest.getLastName());
            return employeeRepository.save(employee);
        }).orElseThrow();
    }


    @DeleteMapping("/employees/{employeeId}")
    public ResponseEntity<?> deleteEmployee(@PathVariable Long employeeId){
        return employeeRepository.findById(employeeId).map(employee -> {
        	employeeRepository.delete(employee);
            return ResponseEntity.ok().build();
        }).orElseThrow();
    }

	
	
	
	
}

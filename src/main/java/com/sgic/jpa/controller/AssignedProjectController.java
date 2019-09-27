package com.sgic.jpa.controller;
import com.sgic.jpa.model.AssignedProject;
import com.sgic.jpa.model.Employee;
import com.sgic.jpa.model.Project;
import com.sgic.jpa.repository.AssignedProjectRepository;
import com.sgic.jpa.repository.EmployeeRepository;
import com.sgic.jpa.repository.ProjectRepository;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;

@RestController
public class AssignedProjectController {

	 	@Autowired
	    private AssignedProjectRepository assignedProjectRepository;

	    @Autowired
	    private ProjectRepository projectRepository;
	    
	    @Autowired
	    private EmployeeRepository employeeRepository;
	    
	    
	    @GetMapping("/projects/{projectId}/employees")
	    public List<Employee> getAllAssignedProjectsByProjectId(@PathVariable (value = "projectId") Long projectId) {
	    	List<AssignedProject> ap = new ArrayList<AssignedProject>();
	    	List<Employee> e = new ArrayList<Employee>();
	        ap =  assignedProjectRepository.findByProjectId(projectId);
	        for (AssignedProject temp : ap) {
				//System.out.println(temp.getEmployee().getId());
				e.addAll(employeeRepository.findAllById(temp.getEmployee().getId()));
				
				 
			}								
	        return e;
	    }
	    
	    
	    @PostMapping("/projects/{projectId}/employees")
	    public AssignedProject createAssignedProject(@PathVariable (value = "projectId") Long projectId,
	                                 @Valid @RequestBody Employee employee,  AssignedProject assignedProject) {
	    	
	    	
	        return projectRepository.findById(projectId).map(project -> {
	        	assignedProject.setProject(project);
	        	
	        	
				//employee.setFirstName(firstName);
				//employee.setLastName(lastName);
	        
	        	employeeRepository.save(employee);
				assignedProject.setEmployee(employee);
				
	            return assignedProjectRepository.save(assignedProject);
	            
	        }).orElseThrow();
	        
	    }
	    
	    @PutMapping("/projects/{projectId}/employees/{employeeId}")
	    public Employee updateEmployee(@PathVariable Long employeeId, @Valid @RequestBody Employee employeeRequest) {
	        return employeeRepository.findById(employeeId).map(employee -> {
	        	employee.setFirstName(employeeRequest.getFirstName());
	        	employee.setLastName(employeeRequest.getLastName());
	            return employeeRepository.save(employee);
	        }).orElseThrow();
	    }
	    
	    
	    
	    @DeleteMapping("/projects/{projectId}/employees/{assignedProjectId}")
	    public ResponseEntity<?> deleteAssignedProject(@PathVariable (value = "projectId") Long projectId,
	                              @PathVariable (value = "assignedProjectId") Long assignedProjectId) {
	        return assignedProjectRepository.findByIdAndProjectId(assignedProjectId, projectId).map(assignedProject -> {
	        	assignedProjectRepository.delete(assignedProject);
	            return ResponseEntity.ok().build();
	        }).orElseThrow();
	    }
	    
}

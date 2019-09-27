package com.sgic.jpa.controller;


import com.sgic.jpa.model.Project;
import com.sgic.jpa.repository.AssignedProjectRepository;
import com.sgic.jpa.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
public class ProjectController {

    @Autowired
    private ProjectRepository projectRepository;
    
    @Autowired
    private AssignedProjectRepository assignedProjectRepository;
  
    
    @GetMapping("/projects")
    public List<Project> getAllProjects() {
        return projectRepository.findAll();
    }
    
    @PostMapping("/projects")
    public Project createProject(@Valid @RequestBody Project project) {
        return projectRepository.save(project);
    }

    @PutMapping("/projects/{projectId}")
    public Project updateProject(@PathVariable Long projectId, @Valid @RequestBody Project projectRequest) {
        return projectRepository.findById(projectId).map(project -> {
        	project.setTitle(projectRequest.getTitle());
        	project.setDescription(projectRequest.getDescription());
            return projectRepository.save(project);
        }).orElseThrow();
    }


    @Transactional
    @DeleteMapping("/projects/{projectId}")
    public ResponseEntity<?> deleteProject(@PathVariable Long projectId) {
        return projectRepository.findById(projectId).map(project -> {
        	assignedProjectRepository.deleteByProjectId(projectId);
            projectRepository.delete(project);
            
            return ResponseEntity.ok().build();
        }).orElseThrow();
    }

    
    
}
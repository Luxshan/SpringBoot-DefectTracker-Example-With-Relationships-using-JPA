package com.sgic.jpa.controller;


import com.sgic.jpa.model.Defect;
import com.sgic.jpa.repository.DefectRepository;
import com.sgic.jpa.repository.ProjectRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import javax.validation.Valid;

@RestController
public class DefectController {

    @Autowired
    private DefectRepository defectRepository;

    @Autowired
    private ProjectRepository projectRepository;
    
    @GetMapping("/projects/{projectId}/defects")
    public List<Defect> getAllDefectsByProjectId(@PathVariable (value = "projectId") Long projectId) {
        return defectRepository.findByProjectId(projectId);
    }
    
    @PostMapping("/projects/{projectId}/defects")
    public Defect createDefect(@PathVariable (value = "projectId") Long projectId,
                                 @Valid @RequestBody Defect defect) {
        return projectRepository.findById(projectId).map(project -> {
            defect.setProject(project);
            return defectRepository.save(defect);
        }).orElseThrow();
    }

    @PutMapping("/projects/{projectId}/defects/{defectId}")
    public Defect updateDefect(@PathVariable (value = "projectId") Long projectId,
                                 @PathVariable (value = "defectId") Long defectId,
                                 @Valid @RequestBody Defect defectRequest) {
        /*
    	if(!projectRepository.existsById(projectId)) {
            throw Error
        }
        */
        return defectRepository.findById(defectId).map(defect -> {
        	defect.setName(defectRequest.getName());
        	defect.setPriority(defectRequest.getPriority());
        	defect.setSeverity(defectRequest.getSeverity());
            return defectRepository.save(defect);
        }).orElseThrow();
    }
    
    @DeleteMapping("/projects/{projectId}/defects/{defectId}")
    public ResponseEntity<?> deleteDefect(@PathVariable (value = "projectId") Long projectId,
                              @PathVariable (value = "defectId") Long defectId) {
        return defectRepository.findByIdAndProjectId(defectId, projectId).map(defect -> {
            defectRepository.delete(defect);
            return ResponseEntity.ok().build();
        }).orElseThrow();
    }
}
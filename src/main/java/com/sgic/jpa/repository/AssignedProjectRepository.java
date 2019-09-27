package com.sgic.jpa.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.sgic.jpa.model.AssignedProject;

import java.util.List;
import java.util.Optional;

@Repository
public interface AssignedProjectRepository extends JpaRepository<AssignedProject, Long> {
	List <AssignedProject> findByProjectId(Long projectId);	
	Optional<AssignedProject> findByIdAndProjectId(Long id, Long projectId);
	long deleteByProjectId(Long projectId);
	
}

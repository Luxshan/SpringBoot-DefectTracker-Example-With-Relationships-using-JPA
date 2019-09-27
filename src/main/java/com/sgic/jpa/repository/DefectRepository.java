package com.sgic.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sgic.jpa.model.Defect;

import java.util.List;
import java.util.Optional;

@Repository
public interface DefectRepository extends JpaRepository<Defect, Long> {
 
	List<Defect> findByProjectId(Long projectId);
	
	Optional<Defect> findByIdAndProjectId(Long id, Long projectId);
	
	
}
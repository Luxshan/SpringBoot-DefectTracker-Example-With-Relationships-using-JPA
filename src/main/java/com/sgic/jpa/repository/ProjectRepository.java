package com.sgic.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sgic.jpa.model.Project;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {

}
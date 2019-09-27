package com.sgic.jpa.model;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "projects")
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(max = 100)
    @Column(unique = true)
    private String title;

    @NotNull
    @Size(max = 250)
    private String description;

    /*
    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                CascadeType.PERSIST,
                CascadeType.MERGE
            })
    @JoinTable(name = "post_tags",
            joinColumns = { @JoinColumn(name = "project_id") },
            inverseJoinColumns = { @JoinColumn(name = "employee_id") })
    private List<Employee> employees;
    */
    
    /*
    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<AssignedProject> assignedProjects;
    */
    public Project() {
    	
    }
    /*
    public Project(String title, String description , AssignedProject... assignedProjects) {
        this.title = title;
        this.description = description;
        for(AssignedProject ap : assignedProjects) ap.setProject(this);
        this.assignedProjects = Stream.of(assignedProjects).collect(Collectors.toList());
    }
    
	

	public List<AssignedProject> getAssignedProjects() {
		return assignedProjects;
	}



	public void setAssignedProjects(List<AssignedProject> assignedProjects) {
		this.assignedProjects = assignedProjects;
	}
*/


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	
    
    
}
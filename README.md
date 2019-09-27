# SpringBoot-DefectTracker-Example-With-Relationships-using-JPA


GET http://localhost:8080/projects

POST http://localhost:8080/projects

PUT http://localhost:8080/projects/{id}  -> project need to be created first
	
DELETE http://localhost:8080/projects{id} -> it will delete all the relationship data as well




GET http://localhost:8080/projects/{id}/defects

POST http://localhost:8080/projects/{id}/defects

PUT http://localhost:8080/projects/{id}/defects/{id}  -> defect need to be created first
	
DELETE http://localhost:8080/projects/{id}/defects/{id}




GET http://localhost:8080/projects/{id}/employees  ->get all employees who got assigned to the project id

POST http://localhost:8080/projects/{id}/employees

PUT http://localhost:8080/projects/{id}/employees/{id}  
	
DELETE http://localhost:8080/projects/{id}/employees/{id} -> only delete the data from assigned employee. Not in the employee table 


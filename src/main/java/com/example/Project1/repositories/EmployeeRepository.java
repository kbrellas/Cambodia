package com.example.Project1.repositories;

import com.example.Project1.models.Employee;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.stereotype.Repository;

@Repository
@RestResource(exported = false)
public interface EmployeeRepository extends CrudRepository<Employee, Long> {
}

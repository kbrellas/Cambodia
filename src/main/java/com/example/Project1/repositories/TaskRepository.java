package com.example.Project1.repositories;

import com.example.Project1.models.Task;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.stereotype.Repository;

@Repository
@RestResource(exported = false)
public interface TaskRepository extends CrudRepository <Task, Long> {

}

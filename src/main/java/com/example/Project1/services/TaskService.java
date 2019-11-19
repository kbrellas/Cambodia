package com.example.Project1.services;

import com.example.Project1.mappers.TaskMapper;
import com.example.Project1.models.*;
import com.example.Project1.models.Error;
import com.example.Project1.repositories.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class TaskService {
    @Autowired
    private
    TaskMapper mapper;

    @Autowired
    private
    TaskRepository repository;

    public GenericResponse<List<TaskResponse>> getAllTasks () {
        try{
            Iterable<Task> tasks = repository.findAll();
            List<TaskResponse> retrievedTasks = mapper.mapAllTasks(tasks);
            if(retrievedTasks == null){
                return new GenericResponse<>(new Error(0,"Empty Repository","There are no Tasks stored"));
            }
            return new GenericResponse<>(retrievedTasks);
        }catch(Exception e){
            e.printStackTrace();
            return new GenericResponse<>(new Error(0,"Internal Server Error", "Unable to retrieve data"));
        }
    }

    public GenericResponse<TaskResponse> getTaskById(long id){
        try {
            Optional<Task> fetchedTask = repository.findById(id);
            if(fetchedTask.isEmpty())
                return new GenericResponse<>(new Error(0,"Invalid Task id", "Unable to detect Task with id: "+String.valueOf(id)));
            Task task = fetchedTask.get();
            return new GenericResponse<>(mapper.mapTaskToTaskResponse(task));
        }
        catch (NoSuchElementException e){
            e.printStackTrace();
            return new GenericResponse<>(new Error(0,"Internal Server Error","Unable to retrieve data"));
        }
    }
}

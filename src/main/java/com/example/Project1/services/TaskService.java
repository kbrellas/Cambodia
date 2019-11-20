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

   public GenericResponse<Task> getTaskById(long id){
        Optional<Task> fetchedTask= repository.findById(id);
        if(!fetchedTask.isPresent()){
            return new GenericResponse<>(new Error(0,"Wrong input error","Task with id : "+id+" does not exist."));
        }
        Task task= fetchedTask.get();
        return new GenericResponse<>(task);

   }

   public GenericResponse<FullTaskInfoResponse> getFullTaskInfoResponse(Task task,List<EmployeeResponse> employees){
        return new GenericResponse<>(mapper.mapFullTaskInfoResponse(task,employees));
   }

    public GenericResponse<Task> assignEmployeeToTask(Employee employee, Task task) {
        List<Employee> listOfEmployees=task.getEmployees();
        listOfEmployees.add(employee);
        task.setEmployees(listOfEmployees);
        repository.save(task);
        return new GenericResponse<>(task);
    }

    public GenericResponse<Task> createNewTask(Task task) {
        repository.save(task);
        return new GenericResponse<>(task);
    }
}

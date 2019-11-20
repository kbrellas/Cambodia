package com.example.Project1.Interactors;

import com.example.Project1.mappers.TaskMapper;
import com.example.Project1.models.*;
import com.example.Project1.models.Error;
import com.example.Project1.repositories.EmployeeRepository;
import com.example.Project1.repositories.TaskRepository;
import com.example.Project1.services.EmployeeService;
import com.example.Project1.services.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class TaskEmployeeInterractor {
    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private TaskService taskService;



    public GenericResponse<Task> addEmployeeToTask (long taskId, long employeeId) {
        GenericResponse<Employee> fetchedEmployee=employeeService.getEmployeeById(employeeId);
        if(fetchedEmployee.getError()!=null){
            return new GenericResponse<>(fetchedEmployee.getError());
        }
        GenericResponse<Task> fetchedTask= taskService.getTaskById(taskId);
        if(fetchedTask.getError()!=null){
            return new GenericResponse<>(fetchedTask.getError());
        }
        GenericResponse<Task> changedTask= taskService.assignEmployeeToTask(fetchedEmployee.getData(),fetchedTask.getData());
        return changedTask;

    }

public GenericResponse<FullTaskInfoResponse> getFullTaskById(long id){
    try {
        GenericResponse<Task> fetchedTask = taskService.getTaskById(id);
        if(fetchedTask.getError()!=null) {
            return new GenericResponse<>(fetchedTask.getError());
        }
        List<Employee> employees =fetchedTask.getData().getEmployees();
        GenericResponse<List<EmployeeResponse>> fetchedEmployees= employeeService.getSpecificEmployees(employees);
        if(fetchedEmployees.getError()!=null){
            return new GenericResponse<>(fetchedEmployees.getError());
        }

        return  taskService.getFullTaskInfoResponse(fetchedTask.getData(),fetchedEmployees.getData());

    }
    catch (NoSuchElementException e){
        e.printStackTrace();
        return new GenericResponse<>(new Error(0,"Internal Server Error","Unable to retrieve data"));
    }
}
}
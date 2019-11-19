package com.example.Project1.Interactors;

import com.example.Project1.models.Employee;
import com.example.Project1.models.Error;
import com.example.Project1.models.GenericResponse;
import com.example.Project1.models.Task;
import com.example.Project1.repositories.EmployeeRepository;
import com.example.Project1.repositories.TaskRepository;
import com.example.Project1.services.EmployeeService;
import com.example.Project1.services.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EmployeeToTaskAssingFunctions {
    @Autowired
    EmployeeService employeeService;
    @Autowired
    TaskService taskService;

    @Autowired
    EmployeeRepository employeeRepository;
    @Autowired
    TaskRepository taskRepository;

    public GenericResponse<Task> addEmployeeToTask (long taskId, long employeeId){
        Optional<Employee> employee = employeeRepository.findById(employeeId);
        Optional<Task> task = taskRepository.findById(taskId);
        if(employee.isEmpty()){
            return new GenericResponse<>(new Error(0,"Not valid Employee id","Unable to detect Employee with id: "+String.valueOf(employeeId)));
        }
        if(task.isEmpty()){
            return new GenericResponse<>(new Error(0,"Not valid Task id","Unable to detect Task with id: "+String.valueOf(taskId)))
        }
        
    }

}

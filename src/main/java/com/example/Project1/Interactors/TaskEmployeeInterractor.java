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

import java.util.ArrayList;
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

    public GenericResponse<List<FullTaskInfoResponse>> getFullTasksByDifficultyAndNumberOfEmployees(String difficulty, long numberOfEmployees) {
        for (Difficulty difficultyValue : Difficulty.values()) {
            if (difficulty.equalsIgnoreCase(difficultyValue.toString())) {
                try {

                    GenericResponse<List<Task>> fetchedTasks = taskService.getAllTasksByDifficulty(difficulty);
                    if (fetchedTasks.getError() != null) {
                        return new GenericResponse<>(fetchedTasks.getError());
                    }
                    List<FullTaskInfoResponse> fullResponse = new ArrayList<>();
                    for (Task task : fetchedTasks.getData()) {
                        List<Employee> employees = new ArrayList<>();
                        if (task.getEmployees().size() == numberOfEmployees) {
                            employees = task.getEmployees();
                            GenericResponse<List<EmployeeResponse>> fetchedEmployees = employeeService.getSpecificEmployees(employees);
                            fullResponse.add(taskService.getFullTaskInfoResponse(task, fetchedEmployees.getData()).getData());
                        }
                    }
                    if(fullResponse.isEmpty()){
                        return new GenericResponse<>(new Error(0,"no tasks found","with employee size : "+numberOfEmployees));
                    }
                    return new GenericResponse<>(fullResponse);
                } catch (NoSuchElementException e) {
                    e.printStackTrace();
                    return new GenericResponse<>(new Error(0,"Internal Server Error","Unable to retrieve data"));
                }
            }
        }
        return new GenericResponse<>(new Error(0,"Wrong difficulty status","Could not match the provided difficulty"));
    }

    public GenericResponse<List<FullTaskInfoResponse>> getFullTasksByDifficulty(String difficulty) {
        for (Difficulty difficultyValue : Difficulty.values()) {
            if (difficulty.equalsIgnoreCase(difficultyValue.toString())) {
                try {
                    GenericResponse<List<Task>> fetchedTasks = taskService.getAllTasksByDifficulty(difficulty);
                    if (fetchedTasks.getError() != null) {
                        return new GenericResponse<>(fetchedTasks.getError());
                    }
                    List<FullTaskInfoResponse> fullResponse = new ArrayList<>();
                    for (Task task : fetchedTasks.getData()) {
                        List<Employee> employees = task.getEmployees();
                        GenericResponse<List<EmployeeResponse>> fetchedEmployees = employeeService.getSpecificEmployees(employees);
                        //if (fetchedEmployees.getError() != null) {
                        //    return new GenericResponse<>(fetchedEmployees.getError());
                        //}
                        fullResponse.add(taskService.getFullTaskInfoResponse(task, fetchedEmployees.getData()).getData());
                    }
                    return new GenericResponse<>(fullResponse);
                } catch (NoSuchElementException e) {
                    e.printStackTrace();
                    return new GenericResponse<>(new Error(0, "Internal Server Error", "Unable to retrieve data"));
                }
            }
        }
        return new GenericResponse<>(new Error(0,"Wrong difficulty status","Could not match the provided difficulty"));
    }

    public GenericResponse<List<FullTaskInfoResponse>> getFullTasksByNumberOfEmployees(long numberOfEmployees) {
        try {
            GenericResponse<List<Task>> fetchedTasks = taskService.getAllTasksByNumberOfEmployees(numberOfEmployees);
            if (fetchedTasks.getError() != null) {
                return new GenericResponse<>(fetchedTasks.getError());
            }
            List<FullTaskInfoResponse> fullResponse = new ArrayList<>();
            for (Task task : fetchedTasks.getData()) {
                List<Employee> employees = task.getEmployees();
                GenericResponse<List<EmployeeResponse>> fetchedEmployees = employeeService.getSpecificEmployees(employees);
                fullResponse.add(taskService.getFullTaskInfoResponse(task, fetchedEmployees.getData()).getData());
            }
            return new GenericResponse<>(fullResponse);
        } catch (NoSuchElementException e) {
            e.printStackTrace();
            return new GenericResponse<>(new Error(0,"Internal Server Error","Unable to retrieve data"));
        }

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


    public GenericResponse<Task> createNewTask(Task task) {
        if(task.getEmployees()==null){
            return taskService.createNewTask(task,null);
        }
        List<Employee> inputedEmployees =task.getEmployees();
        List<Employee> actualEmployees= new ArrayList<>();
        for (Employee employee: inputedEmployees
             ) {
            GenericResponse<Employee> fetchedEmployee= employeeService.getEmployeeById(employee.getId());
            if(fetchedEmployee.getError()!=null){
                return new GenericResponse<>(fetchedEmployee.getError());
            }
            actualEmployees.add(fetchedEmployee.getData());
        }
        return taskService.createNewTask(task,actualEmployees);
    }

    public GenericResponse<Task> updateTask(Task partialTask, long taskId) {
        GenericResponse<Task> fetchedTask= taskService.getTaskById(taskId);
        if(fetchedTask.getError()!=null){
            return new GenericResponse<>(fetchedTask.getError());
        }
        if(partialTask.getEmployees()==null){
            return taskService.updateTask(partialTask,taskId,null);
        }
        List<Employee> inputedEmployees =partialTask.getEmployees();
        List<Employee> actualEmployees= new ArrayList<>();
        for (Employee employee: inputedEmployees
        ) {
            GenericResponse<Employee> fetchedEmployee= employeeService.getEmployeeById(employee.getId());
            if(fetchedEmployee.getError()!=null){
                return new GenericResponse<>(fetchedEmployee.getError());
            }
            actualEmployees.add(fetchedEmployee.getData());
        }
        return taskService.updateTask(partialTask,taskId,actualEmployees);
    }



}

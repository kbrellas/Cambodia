package com.example.Project1.controllers;

import com.example.Project1.Interactors.TaskEmployeeInterractor;
import com.example.Project1.models.*;
import com.example.Project1.models.Error;
import com.example.Project1.services.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TaskController {

    @Autowired
    private TaskService service;

    @Autowired
    private TaskEmployeeInterractor interractor;

    @GetMapping("/allTasks")
    public ResponseEntity getAllTasks() {
        GenericResponse<List<TaskResponse>> response = service.getAllTasks();
        if (response.getError() != null) {
            return new ResponseEntity<>(
                    response.getError(),
                    null,
                    HttpStatus.INTERNAL_SERVER_ERROR
            );
        }

        return new ResponseEntity<>(
                response.getData(),
                null,
                HttpStatus.OK
        );
    }

    @GetMapping("/findTaskById/{taskId}")
    public ResponseEntity getTaskById(@PathVariable long taskId) {
        GenericResponse<FullTaskInfoResponse> response = interractor.getFullTaskById(taskId);
        if (response.getError() != null) {
            return new ResponseEntity<>(
                    response.getError(),
                    null,
                    HttpStatus.INTERNAL_SERVER_ERROR
            );
        }

        return new ResponseEntity<>(
                response.getData(),
                null,
                HttpStatus.OK
        );
    }

    @PostMapping("/createTask")
    public ResponseEntity createTask(@RequestBody Task task){
        GenericResponse<Task> response=service.createNewTask(task);
        if(response.getError()!=null){
            return new ResponseEntity<>(response.getError(),null,HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(response.getData(),null,HttpStatus.CREATED);

    }

    @ExceptionHandler({NumberFormatException.class})
    public ResponseEntity handleNumberFormatException() {
        return new ResponseEntity<>(new Error(0, "Wrong input type", "Id must be type long"),
                null,
                HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({HttpMessageNotReadableException.class})
    public ResponseEntity handleHttpMessageNotReadableException() {
        return new ResponseEntity<>(new Error(0, "Wrong input type(s) given", "Please try again"), null, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}

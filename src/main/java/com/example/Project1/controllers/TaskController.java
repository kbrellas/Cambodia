package com.example.Project1.controllers;

import com.example.Project1.models.EmployeeResponse;
import com.example.Project1.models.GenericResponse;
import com.example.Project1.models.TaskResponse;
import com.example.Project1.services.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TaskController {

    @Autowired
    private TaskService service;

    @GetMapping("/allTasks")
    public ResponseEntity getAllTasks(){
        GenericResponse<List<TaskResponse>> response =service.getAllTasks();
        if (response.getError()!=null){
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

}

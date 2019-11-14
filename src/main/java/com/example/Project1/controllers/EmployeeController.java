package com.example.Project1.controllers;

import com.example.Project1.models.EmployeeResponse;
import com.example.Project1.models.GenericResponse;
import com.example.Project1.models.GetAllEmployeeResponses;
import com.example.Project1.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
public class EmployeeController {

    @Autowired
    private EmployeeService service;

    @GetMapping("/allEmployees")
    public ResponseEntity getAllEmployees(){
        GenericResponse<List<EmployeeResponse>> response =service.getAllEmployees();
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

    @GetMapping("/getEmployeeBySearchCriteria/{searchCriteria}/{searchId}")
    public ResponseEntity getEmployeeBySearchCriteria(@PathVariable String searchCriteria, @PathVariable Long searchId ){
        GenericResponse<List<EmployeeResponse>> response= service.getEmployeeBySearchCriteria(searchCriteria,searchId);
        if(response.getError()!=null){
            return new ResponseEntity<>(
                    response.getError(),
                    null,
                    HttpStatus.BAD_REQUEST
            );
        }

        return new ResponseEntity<>(response.getData(),
                null,
                HttpStatus.OK
        );
    }



}

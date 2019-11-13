package com.example.Project1.controllers;

import com.example.Project1.models.EmployeeResponse;
import com.example.Project1.models.GenericResponse;
import com.example.Project1.models.GetAllEmployeeResponses;
import com.example.Project1.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class EmployeeController {

    @Autowired
    private EmployeeService service;

    @GetMapping("/allEmployees")
    public ResponseEntity getAllEmployees(){
        GenericResponse<List<EmployeeResponse>> response =new GenericResponse(service.getAllEmployees());
        if (response.getError()!=null){
            return new ResponseEntity(
                    response.getError(),
                    null,
                    HttpStatus.NO_CONTENT
            );
        }

        return new ResponseEntity(
                response.getData(),
                null,
                HttpStatus.OK
        );
    }

    @GetMapping("/getEmployeeBySearchCriteria/{searchCriteria}/{searchId}")
    public GetAllEmployeeResponses getEmployeeBySearchCriteria(@PathVariable String searchCriteria, @PathVariable Long searchId ){
        return new GetAllEmployeeResponses(service.getEmployeeBySearchCriteria(searchCriteria,searchId));
    }



}

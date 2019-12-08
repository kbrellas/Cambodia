package com.example.Project1.controllers;

import com.example.Project1.Interactors.TaskEmployeeInterractor;
import com.example.Project1.models.*;
import com.example.Project1.models.Error;
import com.example.Project1.repositories.EmployeeRepository;
import com.example.Project1.repositories.UnitRepository;
import com.example.Project1.services.EmployeeService;
import com.example.Project1.Interactors.EmployeeToUnitAssociator;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class EmployeeController {


    private EmployeeService service;


    private EmployeeToUnitAssociator associator;



    public EmployeeController(EmployeeService service, EmployeeToUnitAssociator associator) {
        this.service = service;
        this.associator = associator;
    }

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
    public ResponseEntity getEmployeeBySearchCriteria(@PathVariable String searchCriteria, @PathVariable Long searchId )throws NumberFormatException{
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

    @PostMapping("/createEmployee")
    public ResponseEntity createEmployee(@RequestBody Employee employee){
        GenericResponse<Employee> response= associator.createEmployee(employee);
        if(response.getError()!=null){
            return new ResponseEntity<>(response.getError(),null,HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(response.getData(),null,HttpStatus.CREATED);
    }

    @PatchMapping("/updateEmployee/{employeeId}")
    public ResponseEntity updateEmployee(@PathVariable Long employeeId, @RequestBody Employee partialEmployee) {

            GenericResponse<Employee> response = associator.updateEmployee(partialEmployee, employeeId);

            if (response.getError() != null) {
                return new ResponseEntity<>(response.getError(), null, HttpStatus.INTERNAL_SERVER_ERROR);
            }
            return new ResponseEntity<>(response.getData(), null, HttpStatus.OK);

    }




    @ExceptionHandler({NumberFormatException.class})
    public ResponseEntity handleNumberFormatException(){
        return new ResponseEntity<>(new Error(0,"Wrong input type", "Id must be type long"),
                null,
                HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({HttpMessageNotReadableException.class})
    public ResponseEntity handleHttpMessageNotReadableException(){
        return new ResponseEntity<>(new Error(0,"Wrong input type(s) given","Please try again"),null,HttpStatus.INTERNAL_SERVER_ERROR);
    }





}

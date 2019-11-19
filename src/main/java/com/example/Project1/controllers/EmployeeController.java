package com.example.Project1.controllers;

import com.example.Project1.models.*;
import com.example.Project1.models.Error;
import com.example.Project1.repositories.EmployeeRepository;
import com.example.Project1.services.EmployeeService;
import com.example.Project1.services.EmployeeToUnitAssociator;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

@RestController
public class EmployeeController {

    @Autowired
    private EmployeeService service;

    @Autowired
    private EmployeeToUnitAssociator associator;

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

    @PostMapping("/createNewEmployee/{unitId}")
    public ResponseEntity createNewEmployee(@RequestBody Employee employee, @PathVariable long unitId){
        GenericResponse<Employee> response =associator.createNewEmployee(employee,unitId);
        if(response.getError()!=null){
            return new ResponseEntity<>(response.getError(),null,HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(response.getData(),null,HttpStatus.CREATED);
    }

    @PatchMapping("/updateEmployee/{employeeId}")
    public ResponseEntity updateEmployee(@PathVariable Long employeeId, @RequestBody Employee partialEmployee) {

            GenericResponse<Employee> response = service.updateEmployee(partialEmployee, employeeId);

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

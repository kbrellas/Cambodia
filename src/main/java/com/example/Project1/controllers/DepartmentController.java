package com.example.Project1.controllers;
import com.example.Project1.Interactors.DepartmentBusinessUnitInterractor;
import com.example.Project1.models.Department;
import com.example.Project1.models.DepartmentResponse;
import com.example.Project1.models.GenericResponse;
import com.example.Project1.services.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class DepartmentController {


    private DepartmentService service;

    @Autowired
    private DepartmentBusinessUnitInterractor interractor;

    public DepartmentController(DepartmentService service) {
        this.service = service;
    }

    @GetMapping("/allDepartments")
    public ResponseEntity getAllDepartments() {
        GenericResponse<List<DepartmentResponse>> response = service.getAllDepartments();
            if(response.getError() != null)
                return new ResponseEntity<>(
                        response.getError(),
                        null,
                        HttpStatus.INTERNAL_SERVER_ERROR
                );

            return new ResponseEntity<>(
                    response.getData(),
                    null,
                    HttpStatus.OK);
    }

    @PostMapping("/createDepartment")
    public ResponseEntity createDepartment(@RequestBody Department department){
        GenericResponse<DepartmentResponse> response= interractor.createDepartment(department);
        if(response.getError()!=null){
            return new ResponseEntity<>(response.getError(),null,HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(response.getData(),null,HttpStatus.CREATED);

    }

    @PatchMapping("/updateDepartment/{departmentId}")
    public ResponseEntity updateDepartment(@PathVariable long departmentId,@RequestBody Department partialDepartment){
        GenericResponse<DepartmentResponse> response= interractor.updateDepartment(partialDepartment,departmentId);
        if(response.getError()!=null){
            return new ResponseEntity<>(response.getError(),null,HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(response.getData(),null,HttpStatus.OK);

    }


}

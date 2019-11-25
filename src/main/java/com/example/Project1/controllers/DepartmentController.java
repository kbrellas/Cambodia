package com.example.Project1.controllers;
import com.example.Project1.models.DepartmentResponse;
import com.example.Project1.models.GenericResponse;
import com.example.Project1.services.DepartmentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class DepartmentController {


    private DepartmentService service;

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
}

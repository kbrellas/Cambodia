package com.example.Project1.controllers;
import com.example.Project1.models.DepartmentResponse;
import com.example.Project1.models.GenericResponse;
import com.example.Project1.models.GetAllDepartmentResponses;
import com.example.Project1.services.DepartmentService;
import com.example.Project1.models.Error;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class DepartmentController {

    @Autowired
    private DepartmentService service;

    @GetMapping("/allDepartments")
    public ResponseEntity getAllDepartments() {
        GenericResponse<List<DepartmentResponse>> response = service.getAllDepartments();
            if(response.getError() != null)
                return new ResponseEntity(
                        response.getError(),
                        null,
                        HttpStatus.INTERNAL_SERVER_ERROR
                );

            return new ResponseEntity(
                    response.getData(),
                    null,
                    HttpStatus.OK);
    }

    @GetMapping("/getDepartmentsByCriteria/{criteria}/{criteriaId}")
    public ResponseEntity getDepartmentsByCriteria(@PathVariable String criteria,
                                             @PathVariable Long criteriaId) {
        if(!criteria.equals("businessUnit"))
            return new ResponseEntity(
                    new Error(0, "Wrong Criteria", "The criteria input should be businessUnit"),
                    null,
                    HttpStatus.BAD_REQUEST
            );
        GenericResponse<List<DepartmentResponse>> response = service.getDepartmentsByCriteria(criteria, criteriaId);
        if(response.getError() != null)
            return new ResponseEntity(
                    response.getError(),
                    null,
                    HttpStatus.BAD_REQUEST
            );

        return new ResponseEntity(
                new GetAllDepartmentResponses(response.getData()),
                null,
                HttpStatus.OK);
    }
}

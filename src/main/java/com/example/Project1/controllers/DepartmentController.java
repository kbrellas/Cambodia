package com.example.Project1.controllers;
import com.example.Project1.models.GetAllDepartmentResponses;
import com.example.Project1.services.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DepartmentController {

    @Autowired
    private DepartmentService service;

    @GetMapping("/allDepartments")
    public GetAllDepartmentResponses getAllDepartments() {
        return new GetAllDepartmentResponses(service.getAllDepartments());
    }

}

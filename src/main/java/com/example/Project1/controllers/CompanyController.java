package com.example.Project1.controllers;

import com.example.Project1.models.GetAllCompanyResponses;
import com.example.Project1.services.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CompanyController {

    @Autowired
    private CompanyService service;

    @GetMapping("/allCompanies")
    public GetAllCompanyResponses getAllCompanies(){
        return new GetAllCompanyResponses(service.getAllCompanies());
    }
}

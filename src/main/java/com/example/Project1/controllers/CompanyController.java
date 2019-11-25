package com.example.Project1.controllers;

import com.example.Project1.services.CompanyService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CompanyController {


    private CompanyService service;

    public CompanyController(CompanyService service){
        this.service=service;
    }

    @GetMapping("/allCompanies")
    public ResponseEntity getAllCompanies() {
        if (service.getAllCompanies().getError()!= null) {
            return new ResponseEntity<>(
                    service.getAllCompanies().getError(),
                    null,
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(
                service.getAllCompanies().getData(),
                null,
                HttpStatus.OK);
    }
}

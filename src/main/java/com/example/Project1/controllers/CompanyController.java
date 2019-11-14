package com.example.Project1.controllers;

import com.example.Project1.services.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CompanyController {

    @Autowired
    private CompanyService service;

    @GetMapping("/allCompanies")
    public ResponseEntity getAllCompanies() {
        if (service.getAllCompanies().getError()!= null) {
            return new ResponseEntity<>(
                    service.getAllCompanies().getError(),
                    null,
                    HttpStatus.OK);
        }
        return new ResponseEntity<>(
                service.getAllCompanies().getData(),
                null,
                HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
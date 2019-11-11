package com.example.Project1.controllers;

import com.example.Project1.models.GetAllBusinessUnitResponses;
import com.example.Project1.services.BusinessUnitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BusinessUnitController {

    @Autowired
    private BusinessUnitService service;

    @GetMapping("/allBusinessUnits")
    public GetAllBusinessUnitResponses getAllBusinessUnits(){
        return new GetAllBusinessUnitResponses(service.getAllBusinessUnits());
    }
}

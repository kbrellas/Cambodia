package com.example.Project1.controllers;

import com.example.Project1.models.GetAllUnitResponses;
import com.example.Project1.services.UnitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UnitController {
    @Autowired
    private UnitService service;

    @GetMapping("/allUnits")
    public GetAllUnitResponses getAllUnits(){

        return new GetAllUnitResponses(service.getAllUnits());
    }
}

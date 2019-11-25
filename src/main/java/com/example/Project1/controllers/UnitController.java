package com.example.Project1.controllers;

import com.example.Project1.models.GenericResponse;
import com.example.Project1.models.UnitResponse;
import com.example.Project1.services.UnitService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UnitController {

    private UnitService service;

    public UnitController(UnitService service) {
        this.service = service;
    }

    @GetMapping("/allUnits")
    public ResponseEntity getAllUnits() {

        GenericResponse<List<UnitResponse>> response = service.getAllUnits();

        if (response.getError()!=null){
            return new ResponseEntity<>(response.getError(),null,HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(response.getData(),null,HttpStatus.OK);
    }
}

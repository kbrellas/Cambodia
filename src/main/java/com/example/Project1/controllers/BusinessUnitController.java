package com.example.Project1.controllers;

import com.example.Project1.models.BusinessUnitResponse;
import com.example.Project1.models.GenericResponse;
import com.example.Project1.services.BusinessUnitService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class BusinessUnitController {

    private BusinessUnitService service;

    public BusinessUnitController(BusinessUnitService service){
        this.service=service;
    }

    @GetMapping("/allBusinessUnits")
    public ResponseEntity getAllBusinessUnits(){
        GenericResponse<List<BusinessUnitResponse>> response = service.getAllBusinessUnits();
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

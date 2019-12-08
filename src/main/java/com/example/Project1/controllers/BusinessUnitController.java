package com.example.Project1.controllers;

import com.example.Project1.Interactors.BusinessUnitCompanyInterractor;
import com.example.Project1.models.BusinessUnit;
import com.example.Project1.models.BusinessUnitResponse;
import com.example.Project1.models.Error;
import com.example.Project1.models.GenericResponse;
import com.example.Project1.services.BusinessUnitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BusinessUnitController {

    private BusinessUnitService service;

    @Autowired
    private BusinessUnitCompanyInterractor interractor;

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

    @PostMapping("/createBusinessUnit")
    public ResponseEntity createBusinessUnit(@RequestBody BusinessUnit businessUnit){
        GenericResponse<BusinessUnitResponse> response = interractor.createBusinessUnit(businessUnit);
        if(response.getError()!=null){
            return new ResponseEntity<>(response.getError(),null,HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(response.getData(),null,HttpStatus.CREATED);


    }

    @PatchMapping("/updateBusinessUnit/{businessUnitId}")
    public ResponseEntity updateBusinessUnit(@RequestBody BusinessUnit partialBusinessUnit, @PathVariable long businessUnitId){
        GenericResponse<BusinessUnitResponse> response= interractor.updateBusinessUnit(partialBusinessUnit,businessUnitId);
        if(response.getError()!=null){
            return new ResponseEntity<>(response.getError(),null,HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(response.getData(),null,HttpStatus.OK);

    }



    @ExceptionHandler({NumberFormatException.class})
    public ResponseEntity handleNumberFormatException(){
        return new ResponseEntity<>(new Error(0,"Wrong input type", "Id must be type long"),
                null,
                HttpStatus.BAD_REQUEST);
    }


}

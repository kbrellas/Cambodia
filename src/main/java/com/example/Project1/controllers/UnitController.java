package com.example.Project1.controllers;

import com.example.Project1.Interactors.UnitDepartmentInterractor;
import com.example.Project1.models.Error;
import com.example.Project1.models.GenericResponse;
import com.example.Project1.models.Unit;
import com.example.Project1.models.UnitResponse;
import com.example.Project1.services.UnitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UnitController {

    private UnitService service;

    @Autowired
    private UnitDepartmentInterractor interractor;

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

    @PostMapping("/createUnit")
    public ResponseEntity createUnit(@RequestBody Unit unit){
        GenericResponse<UnitResponse> response= interractor.createUnit(unit);

        if(response.getError()!=null){
            return new ResponseEntity<>(response.getError(),null,HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(response.getData(),null,HttpStatus.CREATED);

    }

    @PatchMapping("/updateUnit/{unitId}")
    public ResponseEntity updateUnit(@RequestBody Unit partialUnit,@PathVariable long unitId){
        GenericResponse<UnitResponse> response= interractor.updateUnit(partialUnit,unitId);
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

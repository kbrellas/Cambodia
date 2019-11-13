package com.example.Project1.controllers;

import com.example.Project1.models.EmployeeResponse;
import com.example.Project1.models.GenericResponse;
import com.example.Project1.models.GetAllEmployeeResponses;
import com.example.Project1.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class EmployeeController {

    @Autowired
    private EmployeeService service;

    @GetMapping("/allEmployees")
    public ResponseEntity getAllEmployees(){
        GenericResponse<List<EmployeeResponse>> response =new GenericResponse(service.getAllEmployees());
        if (response.getError()!=null){
            return new ResponseEntity(
                    response.getError(),
                    null,
                    HttpStatus.NO_CONTENT
            );
        }

        return new ResponseEntity(
                response.getData(),
                null,
                HttpStatus.OK
        );
    }

    @GetMapping("/getEmployeeBySearchCriteria/{searchCriteria}/{searchId}")
    public GetAllEmployeeResponses getEmployeeBySearchCriteria(@PathVariable String searchCriteria, @PathVariable Long searchId ){
        return new GetAllEmployeeResponses(service.getEmployeeBySearchCriteria(searchCriteria,searchId));
    }

    /*
    @Autowired
    UnitRepository unitRepository;
    @Autowired
    EmployeeRepository employeeRepository;

    @PostMapping("/createEmployee/{UnitId}")
    public ResponseEntity CreateEmployee(@PathVariable(value = "UnitId") Long UnitId, @RequestBody Employee employee) {
        Optional<Unit> unt = unitRepository.findById(UnitId);
        Unit actualUnit = unt.get();
        if (employee.getLeaveDate() != null) {
            if (employee.getHireDate().isAfter(employee.getLeaveDate())) {
                System.out.println("wrong input");
                return new ResponseEntity(HttpStatus.BAD_REQUEST);
            }
        }
        employee.setUnit(actualUnit);
        employeeRepository.save(employee);
        return new ResponseEntity(HttpStatus.OK);

    }

    @PatchMapping("/updateEmployeeUnit/{UnitId}/{EmployeeId}")
    public Employee UpdateEmployeeUnit(@PathVariable(value = "UnitId") Long UnitId, @PathVariable(value = "EmployeeId") Long EmployeeId) {
        Optional<Unit> unt = unitRepository.findById(UnitId);
        Unit actualUnit = unt.get();
        Optional<Employee> emp = employeeRepository.findById(EmployeeId);
        Employee actualEmp = emp.get();
        actualEmp.setUnit(actualUnit);
        employeeRepository.save(actualEmp);
        return actualEmp;
    }

    @PutMapping("/updateEmployee/{UnitId}/{EmployeeId}")
    public Employee UpdateEmployee(@PathVariable(value = "UnitId") Long UnitId, @PathVariable(value = "EmployeeId") Long EmployeeId, @Valid @RequestBody Employee employee) {
        Optional<Unit> unt = unitRepository.findById(UnitId);
        Unit actualUnit = unt.get();
        Optional<Employee> emp = employeeRepository.findById(EmployeeId);
        Employee actualEmp = emp.get();
        BeanUtils.copyProperties(employee, actualEmp, "unit", "id");
        actualEmp.setUnit(actualUnit);

        employeeRepository.save(actualEmp);
        return actualEmp;
    }
    */

}

package com.example.Project1.services;

import com.example.Project1.models.Employee;
import com.example.Project1.models.GenericResponse;
import com.example.Project1.models.Unit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
public class EmployeeToUnitAssociator {

    @Autowired
    private UnitService unitService;

    @Autowired
    private EmployeeService employeeService;

    public GenericResponse<Employee> createNewEmployee(Employee employee, long unitId) {
        GenericResponse<Unit> fetchedUnit=unitService.getUnitById(unitId);
        if(fetchedUnit.getError()!=null){
            return new GenericResponse<>(fetchedUnit.getError());
        }
        GenericResponse<Employee> createResponse=employeeService.createNewEmployee(employee,fetchedUnit.getData());
        if(createResponse.getError()!=null){
            return new GenericResponse<>(createResponse.getError());
        }
        return createResponse;
    }


    public GenericResponse<Employee> updateEmployee(Employee partialEmployee, long employeeId, long unitId) {
        GenericResponse<Unit> fetchedUnit=unitService.getUnitById(unitId);
        if(fetchedUnit.getError()!=null){
            return new GenericResponse<>(fetchedUnit.getError());
        }
        GenericResponse<Employee> changedEmployee=employeeService.updateEmployee(partialEmployee,employeeId,fetchedUnit.getData());
        return changedEmployee;
    }
}

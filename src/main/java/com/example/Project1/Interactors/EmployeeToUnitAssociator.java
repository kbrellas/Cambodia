package com.example.Project1.Interactors;

import com.example.Project1.models.Employee;
import com.example.Project1.models.GenericResponse;
import com.example.Project1.models.Unit;
import com.example.Project1.services.EmployeeService;
import com.example.Project1.services.UnitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
public class EmployeeToUnitAssociator {

    @Autowired
    private UnitService unitService;

    @Autowired
    private EmployeeService employeeService;


    public GenericResponse<Employee> updateEmployee(Employee partialEmployee, long employeeId) {
        if(partialEmployee.getUnit()!=null) {
            GenericResponse<Unit> fetchedUnit = unitService.getUnitById(partialEmployee.getUnit().getId());

            if (fetchedUnit.getError() != null) {
                return new GenericResponse<>(fetchedUnit.getError());
            }
            GenericResponse<Employee> changedEmployee = employeeService.updateEmployee(partialEmployee, employeeId, fetchedUnit.getData());
            return changedEmployee;
        }
        else{
            return employeeService.updateEmployee(partialEmployee,employeeId,null);
        }

    }

    public GenericResponse<Employee> createEmployee(Employee employee) {
        GenericResponse<Unit> fetchedUnit= unitService.getUnitById(employee.getUnit().getId());
        if(fetchedUnit.getError()!=null){
            return new GenericResponse<>(fetchedUnit.getError());
        }
        GenericResponse<Employee> createResponse=employeeService.createNewEmployee(employee,fetchedUnit.getData());
        if(createResponse.getError()!=null){
            return new GenericResponse<>(createResponse.getError());
        }
        return createResponse;
    }
}

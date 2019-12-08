package com.example.Project1.Interactors;

import com.example.Project1.models.Department;
import com.example.Project1.models.Error;
import com.example.Project1.models.GenericResponse;
import com.example.Project1.models.Unit;
import com.example.Project1.models.UnitResponse;
import com.example.Project1.services.DepartmentService;
import com.example.Project1.services.UnitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UnitDepartmentInterractor {

    @Autowired
    private DepartmentService departmentService;

    @Autowired
    private UnitService unitService;

    public GenericResponse<UnitResponse> createUnit(Unit unit) {
        if(unit.getDepartment()!=null){
            GenericResponse<Department> departmentResponse= departmentService.getDepartmentById(unit.getDepartment().getId());
            if(departmentResponse.getError()!=null){
                return new GenericResponse<>(departmentResponse.getError());
            }
            return unitService.createUnit(unit,departmentResponse.getData());
        }
        return new GenericResponse<>(new Error(0,"Wrong Input","Department argument missing"));
    }

    public GenericResponse<UnitResponse> updateUnit(Unit partialUnit, long unitId) {
        if(partialUnit.getDepartment()!=null){
            GenericResponse<Department> departmentResponse= departmentService.getDepartmentById(partialUnit.getDepartment().getId());
            if(departmentResponse.getError()!=null){
                return new GenericResponse<>(departmentResponse.getError());
            }
            GenericResponse<UnitResponse> response= unitService.updateUnit(partialUnit,unitId,departmentResponse.getData());
            if(response.getError()!=null){
                return new GenericResponse<>(response.getError());
            }
            return response;
        }
        GenericResponse<UnitResponse> response = unitService.updateUnit(partialUnit,unitId,null);
        if(response.getError()!=null){
            return new GenericResponse<>(response.getError());
        }
        return response;
    }
}

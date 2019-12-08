package com.example.Project1.Interactors;

import com.example.Project1.models.*;
import com.example.Project1.models.Error;
import com.example.Project1.services.BusinessUnitService;
import com.example.Project1.services.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DepartmentBusinessUnitInterractor {

    @Autowired
    private BusinessUnitService businessUnitService;
    @Autowired
    private DepartmentService departmentService;

    public GenericResponse<DepartmentResponse> createDepartment(Department department) {
        if(department.getBusinessUnit()!=null){
            GenericResponse<BusinessUnit> buResponse= businessUnitService.getBusinessUnitById(department.getBusinessUnit().getId());
            if(buResponse.getError()!=null){
                return new GenericResponse<>(buResponse.getError());
            }

            return departmentService.createDepartment(department,buResponse.getData());


        }
        return new GenericResponse<>(new Error(0,"Wrong input","BusinessUnit argument missing"));
    }

    public GenericResponse<DepartmentResponse> updateDepartment(Department partialDepartment, long departmentId) {
        if(partialDepartment.getBusinessUnit()!=null){
            GenericResponse<BusinessUnit> buResponse= businessUnitService.getBusinessUnitById(partialDepartment.getBusinessUnit().getId());
            if(buResponse.getError()!=null){
                return new GenericResponse<>(buResponse.getError());
            }
            GenericResponse<DepartmentResponse> response = departmentService.updateDepartment(partialDepartment,departmentId,buResponse.getData());
            if(response.getError()!=null){
                return new GenericResponse<>(response.getError());
            }
            return response;
        }
        GenericResponse<DepartmentResponse> response= departmentService.updateDepartment(partialDepartment,departmentId,null);
        if(response.getError()!=null){
            return new GenericResponse<>(response.getError());
        }
        return response;
    }
}

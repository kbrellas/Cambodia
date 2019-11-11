package com.example.Project1.mappers;

import com.example.Project1.models.Department;
import com.example.Project1.models.DepartmentResponse;
import org.springframework.stereotype.Component;

@Component
public class DepartmentMapper {

    public DepartmentResponse mapDepartmentToDepartmentResponse(Department department) {
        return new DepartmentResponse(
                department.getId(),
                department.getName(),
                mapBusinessUnitName(department)
        );
    }

    private String mapBusinessUnitName(Department department) {
        return department.getBusinessUnit().getName();
    }
}

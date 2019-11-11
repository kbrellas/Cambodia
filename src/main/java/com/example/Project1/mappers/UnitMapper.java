package com.example.Project1.mappers;

import com.example.Project1.models.Unit;
import com.example.Project1.models.UnitResponse;
import org.springframework.stereotype.Component;

@Component
public class UnitMapper {


    public UnitResponse mapUnitToUnitResponse(Unit unit) {
        return new UnitResponse(
                unit.getId(),
                unit.getName(),
                mapDepartmentName(unit)
        );
    }

    private String mapDepartmentName(Unit unit) {
        return unit.getDepartment().getName();
    }
}

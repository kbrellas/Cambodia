package com.example.Project1.mappers;

import com.example.Project1.mappers.UnitMapper;
import com.example.Project1.models.Department;
import com.example.Project1.models.Unit;
import com.example.Project1.models.UnitResponse;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class UnitMapperShould {
    private UnitMapper mapper;
    private Unit input;
    private UnitResponse output;
    private String depNameOutput;
    private Department department;

    @Before
    public void setup(){
        mapper = new UnitMapper();
        department = new Department("Marketing");
        department.setId(1);
        input = new Unit("Comms", department);
        output =mapper.mapUnitToUnitResponse(input);
    }

    @Test
    public void keepSameId(){
        Assert.assertEquals(input.getId(),output.getId());
    }

    @Test
    public void keepSameName(){
        Assert.assertEquals(input.getName(),output.getName());
    }

    @Test
    public void keepSameDepartment(){
        Assert.assertEquals(input.getDepartment().getName(),output.getDepartmentName());
    }
}

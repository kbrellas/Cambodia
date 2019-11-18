package com.example.Project1.mappers;

import com.example.Project1.mappers.DepartmentMapper;
import com.example.Project1.models.BusinessUnit;
import com.example.Project1.models.Department;
import com.example.Project1.models.DepartmentResponse;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class DepartmentMapperShould {

    private DepartmentMapper mapper;
    private Department input;
    private DepartmentResponse output;
    private BusinessUnit businessUnit;

    @Before
    public void setup() {
        mapper = new DepartmentMapper();
        businessUnit = new BusinessUnit("BusinessUnit1");
        businessUnit.setId(1);
        input = new Department("HR1",businessUnit);
        output = mapper.mapDepartmentToDepartmentResponse(input);
    }

    @Test
    public void keepSameId() {
        Assert.assertEquals(input.getId(), output.getId());
    }

    @Test
    public void keepSameName() {
        Assert.assertEquals(input.getName(), output.getName());
    }

    @Test
    public void keepSameBusinessUnit() {
        Assert.assertEquals(input.getBusinessUnit().getName(),output.getBusinessUnitName());
    }
}

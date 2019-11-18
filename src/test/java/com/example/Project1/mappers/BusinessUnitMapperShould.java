package com.example.Project1.mappers;

import com.example.Project1.mappers.BusinessUnitMapper;
import com.example.Project1.models.BusinessUnit;
import com.example.Project1.models.BusinessUnitResponse;
import com.example.Project1.models.Company;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class BusinessUnitMapperShould {


    private BusinessUnitMapper mapper;

    private BusinessUnit input;
    private BusinessUnitResponse output;

    private Company mockCompany;

    @Before
    public void setup (){
        mapper = new BusinessUnitMapper();
        mockCompany = new Company("MOCK_COMPANY");
        input = new BusinessUnit("MOCK_BUSINESS_UNIT",mockCompany);
        input.setId(1000);
        output = mapper.mapBusinessUnitToBusinessUnitResponse(input);
    }

    @Test
    public void keepSameId (){
        Assert.assertEquals(input.getId(),output.getId());
    }

    @Test
    public void keepSameName(){
        Assert.assertEquals(input.getName(),output.getName());
    }

    @Test
    public void keepSameCompanyName(){
        Assert.assertEquals(input.getCompany().getName(),output.getCompanyName());
    }

}

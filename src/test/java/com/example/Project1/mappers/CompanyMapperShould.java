package com.example.Project1.mappers;

import com.example.Project1.mappers.CompanyMapper;
import com.example.Project1.models.Company;
import com.example.Project1.models.CompanyResponse;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


public class CompanyMapperShould {

    private Company companyInput;
    private CompanyResponse companyResponseOutput;


    @Before
    public void setup(){
        CompanyMapper mapper=new CompanyMapper();
    companyInput=new Company("test Company");
    companyInput.setId(111);
    companyResponseOutput=mapper.mapCompanyToCompanyResponse(companyInput);
    }

    @Test
    public void keepSameName(){
        Assert.assertEquals(companyInput.getName(),companyResponseOutput.getName());

    }

    @Test
    public void keepSameId(){
        Assert.assertEquals(companyInput.getId(),companyResponseOutput.getId());

    }

}

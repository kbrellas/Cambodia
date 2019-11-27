package com.example.Project1.controllers;

import com.example.Project1.models.CompanyResponse;
import com.example.Project1.models.Error;
import com.example.Project1.models.GenericResponse;
import com.example.Project1.services.CompanyService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.hasItems;
import static org.mockito.Mockito.when;

public class CompanyControllerShould {
    private CompanyController controller;

    @Mock
    CompanyService service;

    @Mock
    CompanyResponse companyResponse1;

    @Mock
    CompanyResponse companyResponse2;

    @Mock
    CompanyResponse companyResponse3;


    @Before
    public  void setup(){
        MockitoAnnotations.initMocks(this);
        List<CompanyResponse> mockedCompanyResponseList = new ArrayList<>();
        mockedCompanyResponseList.add(companyResponse1);
        mockedCompanyResponseList.add(companyResponse2);
        mockedCompanyResponseList.add(companyResponse3);
        GenericResponse<List<CompanyResponse>> serviceResponse=new GenericResponse<>(mockedCompanyResponseList);
        when(service.getAllCompanies()).thenReturn(serviceResponse);
        controller=new CompanyController(service);
    }
    @Test
    public void returnAllCompanies(){
        ResponseEntity<List<CompanyResponse>> actual=controller.getAllCompanies();
        Assert.assertThat(actual.getBody(),hasItems(companyResponse1,companyResponse2,companyResponse3));
        Assert.assertEquals(HttpStatus.OK,actual.getStatusCode());
    }

    @Test
    public void returnErrorWhenAllCompaniesServiceFails(){
        Error error=MockServiceFailure();
        ResponseEntity actual= controller.getAllCompanies();
        Assert.assertEquals(error,actual.getBody());
        Assert.assertEquals(HttpStatus.INTERNAL_SERVER_ERROR,actual.getStatusCode());
    }


    private Error MockServiceFailure() {
        Error error = new Error(0,"Error","Something went wrong");
        when(service.getAllCompanies()).thenReturn(new GenericResponse<>(error));
        return error;
    }


}

package com.example.Project1.controllers;

import com.example.Project1.models.BusinessUnitResponse;
import com.example.Project1.models.Error;
import com.example.Project1.models.GenericResponse;
import com.example.Project1.services.BusinessUnitService;
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

public class BusinessUnitControllerShould {
    private BusinessUnitController controller;

    @Mock
    private BusinessUnitService service;

    @Mock
    private BusinessUnitResponse bu1;
    @Mock
    private BusinessUnitResponse bu2;
    @Mock
    private BusinessUnitResponse bu3;

    @Before
    public  void setup(){
        MockitoAnnotations.initMocks(this);
        List<BusinessUnitResponse> mockedBusinessUnitResponseList = new ArrayList<>();
        mockedBusinessUnitResponseList.add(bu1);
        mockedBusinessUnitResponseList.add(bu2);
        mockedBusinessUnitResponseList.add(bu3);
        GenericResponse<List<BusinessUnitResponse>> serviceResponse=new GenericResponse<>(mockedBusinessUnitResponseList);
        when(service.getAllBusinessUnits()).thenReturn(serviceResponse);
        controller=new BusinessUnitController(service);
    }
    @Test
    public void returnAllBusinessUnits(){
        ResponseEntity<List<BusinessUnitResponse>> actual=controller.getAllBusinessUnits();
        Assert.assertThat(actual.getBody(),hasItems(bu1,bu2,bu3));
        Assert.assertEquals(HttpStatus.OK,actual.getStatusCode());
    }

    @Test
    public void returnErrorWhenAllBusinessUnitsServiceFails(){
        Error error=MockServiceFailure();
        ResponseEntity actual= controller.getAllBusinessUnits();
        Assert.assertEquals(error,actual.getBody());
        Assert.assertEquals(HttpStatus.INTERNAL_SERVER_ERROR,actual.getStatusCode());
    }


    private Error MockServiceFailure() {
        Error error = new Error(0,"Error","Something went wrong");
        when(service.getAllBusinessUnits()).thenReturn(new GenericResponse<>(error));
        return error;
    }


}

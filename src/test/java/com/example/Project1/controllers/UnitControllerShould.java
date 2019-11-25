package com.example.Project1.controllers;

import com.example.Project1.models.UnitResponse;
import com.example.Project1.models.Error;
import com.example.Project1.models.GenericResponse;
import com.example.Project1.services.UnitService;
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

public class UnitControllerShould {
    private UnitController controller;

    @Mock
    UnitService service;

    @Mock
    UnitResponse unitResponse1;

    @Mock
    UnitResponse unitResponse2;

    @Mock
    UnitResponse unitResponse3;


    @Before
    public  void setup(){
        MockitoAnnotations.initMocks(this);
        List<UnitResponse> mockedUnitResponseList = new ArrayList<>();
        mockedUnitResponseList.add(unitResponse1);
        mockedUnitResponseList.add(unitResponse2);
        mockedUnitResponseList.add(unitResponse3);
        GenericResponse<List<UnitResponse>> serviceResponse=new GenericResponse<>(mockedUnitResponseList);
        when(service.getAllUnits()).thenReturn(serviceResponse);
        controller=new UnitController(service);
    }
    @Test
    public void returnAllUnits(){
        ResponseEntity<List<UnitResponse>> actual=controller.getAllUnits();
        Assert.assertThat(actual.getBody(),hasItems(unitResponse1,unitResponse2,unitResponse3));
        Assert.assertEquals(HttpStatus.OK,actual.getStatusCode());
    }

    @Test
    public void returnErrorWhenAllUnitsServiceFails(){
        Error error=MockServiceFailure();
        ResponseEntity actual= controller.getAllUnits();
        Assert.assertEquals(error,actual.getBody());
        Assert.assertEquals(HttpStatus.INTERNAL_SERVER_ERROR,actual.getStatusCode());
    }


    private Error MockServiceFailure() {
        Error error = new Error(0,"Error","Something went wrong");
        when(service.getAllUnits()).thenReturn(new GenericResponse<>(error));
        return error;
    }

}

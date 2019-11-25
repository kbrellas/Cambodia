package com.example.Project1.controllers;

import com.example.Project1.models.DepartmentResponse;
import com.example.Project1.models.Error;
import com.example.Project1.models.GenericResponse;
import com.example.Project1.services.DepartmentService;
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

public class DepartmentControllerShould {
    private DepartmentController controller;

    @Mock
    DepartmentService service;

    @Mock
    DepartmentResponse departmentResponse1;

    @Mock
    DepartmentResponse departmentResponse2;

    @Mock
    DepartmentResponse departmentResponse3;


    @Before
    public  void setup(){
        MockitoAnnotations.initMocks(this);
        List<DepartmentResponse> mockedDepartmentResponseList = new ArrayList<>();
        mockedDepartmentResponseList.add(departmentResponse1);
        mockedDepartmentResponseList.add(departmentResponse2);
        mockedDepartmentResponseList.add(departmentResponse3);
        GenericResponse<List<DepartmentResponse>> serviceResponse=new GenericResponse<>(mockedDepartmentResponseList);
        when(service.getAllDepartments()).thenReturn(serviceResponse);
        controller=new DepartmentController(service);
    }
    @Test
    public void returnAllDepartments(){
        ResponseEntity<List<DepartmentResponse>> actual=controller.getAllDepartments();
        Assert.assertThat(actual.getBody(),hasItems(departmentResponse1,departmentResponse2,departmentResponse3));
        Assert.assertEquals(HttpStatus.OK,actual.getStatusCode());
    }

    @Test
    public void returnErrorWhenAllDepartmentsServiceFails(){
        Error error=MockServiceFailure();
        ResponseEntity actual= controller.getAllDepartments();
        Assert.assertEquals(error,actual.getBody());
        Assert.assertEquals(HttpStatus.INTERNAL_SERVER_ERROR,actual.getStatusCode());
    }


    private Error MockServiceFailure() {
        Error error = new Error(0,"Error","Something went wrong");
        when(service.getAllDepartments()).thenReturn(new GenericResponse<>(error));
        return error;
    }

}

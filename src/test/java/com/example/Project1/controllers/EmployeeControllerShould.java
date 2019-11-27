package com.example.Project1.controllers;

import com.example.Project1.Interactors.EmployeeToUnitAssociator;
import com.example.Project1.models.Employee;
import com.example.Project1.models.EmployeeResponse;
import com.example.Project1.models.Error;
import com.example.Project1.models.GenericResponse;
import com.example.Project1.services.EmployeeService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.hasItems;
import static org.mockito.Mockito.when;

public class EmployeeControllerShould {
    private EmployeeController controller;

    @Mock
    private EmployeeService service;

    @Mock
    private EmployeeResponse employee1;

    @Mock
    private EmployeeResponse employee2;

    @Mock
    private EmployeeToUnitAssociator associator;

    @Mock
    private Employee employee;


    @Before
    public void setup(){
        MockitoAnnotations.initMocks(this);
        List<EmployeeResponse> mockedEmployees= new ArrayList<>();
        mockedEmployees.add(employee1);
        mockedEmployees.add(employee2);
        GenericResponse<List<EmployeeResponse>> mockedResponse= new GenericResponse<>(mockedEmployees);
        when(service.getAllEmployees()).thenReturn(mockedResponse);
        when(service.getEmployeeBySearchCriteria(ArgumentMatchers.any(),ArgumentMatchers.any())).thenReturn(mockedResponse);
        when(associator.createEmployee(ArgumentMatchers.any())).thenReturn(new GenericResponse<>(employee));
        when(associator.updateEmployee(employee,(long)1)).thenReturn(new GenericResponse<>(employee));
        controller=new EmployeeController(service,associator);
    }

    @Test
    public void returnAllEmployees(){
        ResponseEntity<List<EmployeeResponse>> actual=controller.getAllEmployees();

        Assert.assertThat(actual.getBody(),hasItems(employee1,employee2));
        Assert.assertEquals(HttpStatus.OK,actual.getStatusCode());
    }

    @Test
    public void returnErrorWhenAllEmployeesServiceFails(){
        Error error= MockServiceFailure();
        ResponseEntity<List<EmployeeResponse>> actual= controller.getAllEmployees();

        Assert.assertEquals(error,actual.getBody());
        Assert.assertEquals(HttpStatus.INTERNAL_SERVER_ERROR,actual.getStatusCode());

    }

    private Error MockServiceFailure() {
        Error error = new Error(0,"Error","Something went wrong");
        when(service.getAllEmployees()).thenReturn(new GenericResponse<>(error));
        when(service.getEmployeeBySearchCriteria(ArgumentMatchers.any(),ArgumentMatchers.any())).thenReturn(new GenericResponse<>(error));
        when(associator.createEmployee(ArgumentMatchers.any())).thenReturn(new GenericResponse<>(error));
        when(associator.updateEmployee(employee,(long)1)).thenReturn(new GenericResponse<>(error));
        controller=new EmployeeController(service,associator);
        return error;

    }

    @Test
    public void returnEmployeesBySearchCriteria(){
        ResponseEntity<List<EmployeeResponse>> actual= controller.getEmployeeBySearchCriteria("unit",(long)3);

        Assert.assertThat(actual.getBody(),hasItems(employee1,employee2));
        Assert.assertEquals(HttpStatus.OK,actual.getStatusCode());
    }

    @Test
    public void returnErrorWhenEmployeeBySearchCriteriaServiceFails(){
        Error error = MockServiceFailure();
        ResponseEntity<List<EmployeeResponse>> actual = controller.getEmployeeBySearchCriteria("unit",(long)2);

        Assert.assertEquals(error, actual.getBody());
        Assert.assertEquals(HttpStatus.BAD_REQUEST, actual.getStatusCode());
    }

    @Test
    public void returnEmployeeWhenCreated(){
        ResponseEntity<Employee> actual= controller.createEmployee(employee);

        Assert.assertEquals(employee,actual.getBody());
        Assert.assertEquals(HttpStatus.CREATED,actual.getStatusCode());
    }

    @Test
    public void returnErrorWhenCreateEmployeeFails(){
        Error error =MockServiceFailure();
        ResponseEntity<Employee> actual= controller.createEmployee(employee);

        Assert.assertEquals(error,actual.getBody());
        Assert.assertEquals(HttpStatus.INTERNAL_SERVER_ERROR,actual.getStatusCode());

    }

    @Test
    public void returnUpdatedEmployeeWhenUpdateEmployee(){
        ResponseEntity<Employee> actual= controller.updateEmployee((long)1,employee);

        Assert.assertEquals(employee,actual.getBody());
        Assert.assertEquals(HttpStatus.OK,actual.getStatusCode());
    }
    @Test
    public void returnErrorWhenUpdateFails(){
        Error error = MockServiceFailure();
        ResponseEntity<Employee> actual = controller.updateEmployee((long)1,employee);

        Assert.assertEquals(error,actual.getBody());
        Assert.assertEquals(HttpStatus.INTERNAL_SERVER_ERROR,actual.getStatusCode());

    }


}

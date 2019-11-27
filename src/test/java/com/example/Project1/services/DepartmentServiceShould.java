package com.example.Project1.services;

import com.example.Project1.mappers.DepartmentMapper;
import com.example.Project1.models.*;
import com.example.Project1.repositories.DepartmentRepository;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

public class DepartmentServiceShould {
    private DepartmentService service;
    private BusinessUnit businessUnit;
    private Department department;
    private Company company;
    @Mock
    private DepartmentRepository repository;
    @Mock
    private DepartmentMapper mapper;

    private Iterable<Department> mockedDepartments = new ArrayList<Department>(){
        {
            add(new Department((long) 1,"Department 1",businessUnit));
            add(new Department((long) 2,"Department 2",businessUnit));
        }
    };

    @Before
    public void setup(){
        MockitoAnnotations.initMocks(this);
        company= new Company(1,"Unisystems");
        businessUnit = new BusinessUnit(1,"Business Unit 1",company);
        when(repository.findAll()).thenReturn(mockedDepartments);
        when(repository.findById((long)1)).thenReturn(java.util.Optional.of(new Department((long) 1, "Department 1",businessUnit)));
        when(mapper.mapDepartmentToDepartmentResponse(ArgumentMatchers.any())).thenReturn(new DepartmentResponse((long) 1,"Department 1",businessUnit.getName()));
        service = new DepartmentService(mapper,repository);
        department =new Department(1,"Department 1",businessUnit);
    }

    @Test
    public void retrieveDepartmentsFromRepository(){
        service.getAllDepartments();
        Mockito.verify(repository).findAll();
    }

    @Test
    public void retrieveDepartmentFromRepository(){
        service.getDepartmentById((long) 1);
        Mockito.verify(repository).findById((long) 1);
    }

    @Test
    public void useDepartmentMapper(){
        service.getAllDepartments();
        // ArgumentMatchers.any() gia na elenxo an kalesthke me opoiadhpote sunarthsh
        Mockito.verify(mapper,times(2)).mapDepartmentToDepartmentResponse(ArgumentMatchers.any());
    }

    @Test
    public void returnListOfDepartmentResponse(){
        GenericResponse<List<DepartmentResponse>> output = service.getAllDepartments();
        Assert.assertEquals(2,output.getData().size());
        List<DepartmentResponse> expextedList=new ArrayList<>();
        expextedList.add(new DepartmentResponse((long) 1,"Department 1",businessUnit.getName()));
        expextedList.add(new DepartmentResponse((long) 2,"Department 2",businessUnit.getName()));
        Assert.assertThat(expextedList, Matchers.samePropertyValuesAs(output.getData()));
    }

    @Test
    public void returnDepartmentById(){
        Department output = service.getDepartmentById((long) 1).getData();
        Assert.assertThat(department,Matchers.samePropertyValuesAs(output));
    }
}

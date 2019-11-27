package com.example.Project1.services;

import com.example.Project1.mappers.UnitMapper;
import com.example.Project1.models.*;
import com.example.Project1.repositories.UnitRepository;
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

public class UnitServiceShould {
    private UnitService service;
    private BusinessUnit businessUnit;
    private Department department;
    private Company company;
    private Unit unit;
    @Mock
    private UnitRepository repository;
    @Mock
    private UnitMapper mapper;

    private Iterable<Unit> mockedUnits = new ArrayList<Unit>(){
        {
            add(new Unit((long) 1,"Unit 1",department));
            add(new Unit((long) 2,"Unit 2",department));
        }
    };

    @Before
    public void setup(){
        MockitoAnnotations.initMocks(this);
        company= new Company(1,"Unisystems");
        businessUnit = new BusinessUnit(1,"Business Unit 1",company);
        department =new Department(1,"Department 1",businessUnit);
        when(repository.findAll()).thenReturn(mockedUnits);
        when(repository.findById((long)1)).thenReturn(java.util.Optional.of(new Unit((long) 1, "Unit 1",department)));
        when(mapper.mapUnitToUnitResponse(ArgumentMatchers.any())).thenReturn(new UnitResponse((long) 1,"Unit 1",department.getName()));
        service = new UnitService(mapper,repository);
        unit = new Unit(1,"Unit 1",department);
    }

    @Test
    public void retrieveUnitsFromRepository(){
        service.getAllUnits();
        Mockito.verify(repository).findAll();
    }

    @Test
    public void retrieveUnitFromRepository(){
        service.getUnitById((long) 1);
        Mockito.verify(repository).findById((long) 1);
    }

    @Test
    public void useUnitMapper(){
        service.getAllUnits();
        // ArgumentMatchers.any() gia na elenxo an kalesthke me opoiadhpote sunarthsh
        Mockito.verify(mapper,times(2)).mapUnitToUnitResponse(ArgumentMatchers.any());
    }

    @Test
    public void returnListOfUnitResponse(){
        GenericResponse<List<UnitResponse>> output = service.getAllUnits();
        Assert.assertEquals(2,output.getData().size());
        List<UnitResponse> expextedList=new ArrayList<>();
        expextedList.add(new UnitResponse((long) 1,"Unit 1",department.getName()));
        expextedList.add(new UnitResponse((long) 2,"Unit 2",department.getName()));
        Assert.assertThat(expextedList, Matchers.samePropertyValuesAs(output.getData()));
    }

    @Test
    public void returnUnitById(){
        Unit output = service.getUnitById((long) 1).getData();
        Assert.assertThat(unit,Matchers.samePropertyValuesAs(output));
    }
}

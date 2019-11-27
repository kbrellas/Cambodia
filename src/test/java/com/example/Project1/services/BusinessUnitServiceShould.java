package com.example.Project1.services;

import com.example.Project1.mappers.BusinessUnitMapper;
import com.example.Project1.models.*;
import com.example.Project1.repositories.BusinessUnitRepository;
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

public class BusinessUnitServiceShould {
    private BusinessUnitService service;
    private BusinessUnit businessUnit;
    private Company company;
    @Mock
    private BusinessUnitRepository repository;
    @Mock
    private BusinessUnitMapper mapper;

    private Iterable<BusinessUnit> mockedBusinessUnits = new ArrayList<BusinessUnit>(){
        {
            add(new BusinessUnit((long) 1,"Business Unit 1",company));
            add(new BusinessUnit((long) 2,"Business Unit 2",company));
        }
    };

    @Before
    public void setup(){
        MockitoAnnotations.initMocks(this);
        company = new Company(1,"Unisystems");
        when(repository.findAll()).thenReturn(mockedBusinessUnits);
        when(repository.findById((long)1)).thenReturn(java.util.Optional.of(new BusinessUnit((long) 1, "Business Unit 1",company)));
        when(mapper.mapBusinessUnitToBusinessUnitResponse(ArgumentMatchers.any())).thenReturn(new BusinessUnitResponse((long) 1,"Business Unit 1",company.getName()));
        service = new BusinessUnitService(mapper,repository);
        businessUnit =new BusinessUnit(1,"Business Unit 1",company);
    }

    @Test
    public void retrieveBusinessUnitsFromRepository(){
        service.getAllBusinessUnits();
        Mockito.verify(repository).findAll();
    }

    @Test
    public void retrieveBusinessUnitFromRepository(){
        service.getBusinessUnitById((long) 1);
        Mockito.verify(repository).findById((long) 1);
    }

    @Test
    public void useBusinessUnitMapper(){
        service.getAllBusinessUnits();
        // ArgumentMatchers.any() gia na elenxo an kalesthke me opoiadhpote sunarthsh
        Mockito.verify(mapper,times(2)).mapBusinessUnitToBusinessUnitResponse(ArgumentMatchers.any());
    }

    @Test
    public void returnListOfBusinessUnitResponse(){
        GenericResponse<List<BusinessUnitResponse>> output = service.getAllBusinessUnits();
        Assert.assertEquals(2,output.getData().size());
        List<BusinessUnitResponse> expextedList=new ArrayList<>();
        expextedList.add(new BusinessUnitResponse((long) 1,"Business Unit 1",company.getName()));
        expextedList.add(new BusinessUnitResponse((long) 2,"Business Unit 2",company.getName()));
        Assert.assertThat(expextedList, Matchers.samePropertyValuesAs(output.getData()));
    }

    @Test
    public void returnBusinessUnitById(){
        BusinessUnit output = service.getBusinessUnitById((long) 1).getData();
        Assert.assertThat(businessUnit,Matchers.samePropertyValuesAs(output));
    }
}

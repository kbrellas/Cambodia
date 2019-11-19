package com.example.Project1.services;

import com.example.Project1.mappers.CompanyMapper;
import com.example.Project1.models.Company;
import com.example.Project1.models.CompanyResponse;
import com.example.Project1.models.GenericResponse;
import com.example.Project1.repositories.CompanyRepository;
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

public class CompanyServiceShould {
    private CompanyService service;
    private Company company;
    @Mock
    private CompanyRepository repository;
    @Mock
    private CompanyMapper mapper;

    private Iterable<Company> mockedCompanies = new ArrayList<Company>(){
        {
            add(new Company(1,"Unisystems"));
            add(new Company(2,"InfoQuest"));
        }
    };

    @Before
    public void setup(){
        MockitoAnnotations.initMocks(this);
        when(repository.findAll()).thenReturn(mockedCompanies);
        when(repository.findById((long)1)).thenReturn(java.util.Optional.of(new Company((long) 1, "Unisystems")));
        when(mapper.mapCompanyToCompanyResponse(ArgumentMatchers.any())).thenReturn(new CompanyResponse((long) 1,"Unisystems"));
        service = new CompanyService(mapper,repository);
        company=new Company(1,"Unisystems");
    }

    @Test
    public void retrieveCompaniesFromRepository(){
        service.getAllCompanies();
        Mockito.verify(repository).findAll();
    }

    @Test
    public void retrieveCompanyFromRepository(){
        service.getCompanyById((long) 1);
        Mockito.verify(repository).findById((long) 1);
    }

    @Test
    public void useCompanyMapper(){
        service.getAllCompanies();
        // ArgumentMatchers.any() gia na elenxo an kalesthke me opoiadhpote sunarthsh
        Mockito.verify(mapper,times(2)).mapCompanyToCompanyResponse(ArgumentMatchers.any());
    }

    @Test
    public void returnListOfCompanyResponse(){
        GenericResponse<List<CompanyResponse>> output = service.getAllCompanies();
        Assert.assertEquals(2,output.getData().size());
        List<CompanyResponse> expextedList=new ArrayList<>();
        expextedList.add(new CompanyResponse((long) 1,"Unisystems"));
        expextedList.add(new CompanyResponse((long) 2,"InfoQuest"));
        Assert.assertThat(expextedList, Matchers.samePropertyValuesAs(output.getData()));
    }

    @Test
    public void returnCompanyById(){
        Company output = service.getCompanyById((long) 1).getData();
        Assert.assertThat(company,Matchers.samePropertyValuesAs(output));
    }

}

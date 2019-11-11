package com.example.Project1.services;

import com.example.Project1.mappers.BusinessUnitMapper;
import com.example.Project1.repositories.BusinessUnitRepository;
import com.example.Project1.models.BusinessUnit;
import com.example.Project1.models.BusinessUnitResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BusinessUnitService {

    @Autowired
    private BusinessUnitMapper mapper;

    @Autowired
    private BusinessUnitRepository repository;

    public List<BusinessUnitResponse> getAllBusinessUnits(){
        Iterable<BusinessUnit> retrievedBusinessUnit= repository.findAll();
        List<BusinessUnitResponse> businessUnits= new ArrayList<>();
        for (BusinessUnit businessUnit: retrievedBusinessUnit
             ) {
            businessUnits.add(mapper.mapBusinessUnitToBusinessUnitResponse(businessUnit));
        }
        return businessUnits;

    }


}

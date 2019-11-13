package com.example.Project1.services;

import com.example.Project1.mappers.BusinessUnitMapper;
import com.example.Project1.models.Error;
import com.example.Project1.models.GenericResponse;
import com.example.Project1.models.Unit;
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

    public GenericResponse<List<BusinessUnitResponse>> getAllBusinessUnits(){
        try{
            Iterable<BusinessUnit> businessUnits = repository.findAll();
            List<BusinessUnitResponse> retrievedBusinessUnits = new ArrayList<>();
            for (BusinessUnit businessUnit : businessUnits){
                retrievedBusinessUnits.add(mapper.mapBusinessUnitToBusinessUnitResponse(businessUnit));

            }
            if(retrievedBusinessUnits.isEmpty()){
                return new GenericResponse<>(new Error(0,"Empty Repository","There are no Units stored"));
            }
            return new GenericResponse<>(retrievedBusinessUnits);
        }catch (Exception e){
            e.printStackTrace();

            Error error = new Error(0,"Internal Server Error", "Unable to retrieve data");
            return new GenericResponse<>(error);
        }

    }
}

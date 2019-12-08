package com.example.Project1.services;

import com.example.Project1.mappers.BusinessUnitMapper;
import com.example.Project1.models.*;
import com.example.Project1.models.Error;
import com.example.Project1.repositories.BusinessUnitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.*;

@Service
public class BusinessUnitService {

    private BusinessUnitMapper mapper;

    private BusinessUnitRepository repository;

    public BusinessUnitService(BusinessUnitMapper mapper, BusinessUnitRepository repository) {
        this.mapper = mapper;
        this.repository = repository;
    }

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

    public GenericResponse<BusinessUnit> getBusinessUnitById(long id){
        try {
            Optional<BusinessUnit> fetchedBusinessUnit = repository.findById(id);
            BusinessUnit businessUnit= fetchedBusinessUnit.get();
            return new GenericResponse<>(businessUnit);
        }
        catch (NoSuchElementException e){
            e.printStackTrace();
            return new GenericResponse<>(new Error(0,"Wrong id for Business Unit","Id : "+id+" does not exist" ));
        }
    }

    public GenericResponse<BusinessUnitResponse> createBusinessUnit(BusinessUnit businessUnit, Company company) {
        businessUnit.setCompany(company);
        repository.save(businessUnit);
        return new GenericResponse<>(mapper.mapBusinessUnitToBusinessUnitResponse(businessUnit));
    }

    public GenericResponse<BusinessUnitResponse> updateBusinessUnit(BusinessUnit partialBusinessUnit,long businessUnitId, @Nullable Company company) {
        Optional<BusinessUnit> optionalBusinessUnit=repository.findById(businessUnitId);
        if(!optionalBusinessUnit.isPresent()){
            return new GenericResponse<>(new Error(0,"Wrong input","BusinessUnit with id :"+businessUnitId+" does not exist"));
        }

        BusinessUnit fetchedBusinessUnit= optionalBusinessUnit.get();

        Map<String, Object> businessUnitMap = new HashMap<>();
        Field[] allFields = partialBusinessUnit.getClass().getDeclaredFields();
        for (Field field : allFields) {
            field.setAccessible(true);
            try {

                if(!field.getName().equalsIgnoreCase("id")) {
                    Object value = field.get(partialBusinessUnit);
                    if (value != null&& !value.equals(0)) {
                        businessUnitMap.put(field.getName(), value);
                    }
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
                return new GenericResponse<>(new Error(0, "Cannot Access field", "Field : "+field));
            }
            field.setAccessible(false);
        }

        businessUnitMap.forEach((k, v) -> {
            // use reflection to get field k on retrievedEmployee and set it to value k
            Field field = ReflectionUtils.findField(BusinessUnit.class, k);
            field.setAccessible(true);
            ReflectionUtils.setField(field, fetchedBusinessUnit, v);
            field.setAccessible(false);
        });

        if(company!=null){
            fetchedBusinessUnit.setCompany(company);
        }
        repository.save(fetchedBusinessUnit);
        return new GenericResponse<>(mapper.mapBusinessUnitToBusinessUnitResponse(fetchedBusinessUnit));
    }


}

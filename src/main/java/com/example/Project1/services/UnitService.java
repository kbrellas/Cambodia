package com.example.Project1.services;

import com.example.Project1.mappers.UnitMapper;
import com.example.Project1.models.Error;
import com.example.Project1.models.GenericResponse;
import com.example.Project1.repositories.UnitRepository;
import com.example.Project1.models.Unit;
import com.example.Project1.models.UnitResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class UnitService {

    @Autowired
    private UnitMapper mapper;

    @Autowired
    private UnitRepository repository;

    public GenericResponse<List<UnitResponse>> getAllUnits(){
        try {
            Iterable<Unit> retrievedUnits = repository.findAll();
            List<UnitResponse> units = new ArrayList<>();
            for (Unit unit : retrievedUnits) {
                units.add(mapper.mapUnitToUnitResponse(unit));
            }
            if(units.isEmpty()){
                return new GenericResponse<>(new Error(0,"Empty Repository","There are no Units stored"));
            }
            return new GenericResponse<>(units);
        }catch(Exception e){
            e.printStackTrace();
            return new GenericResponse<>(new Error(0,"Internal Server Error", "Unable to retrieve data"));
        }
    }

    public GenericResponse<Unit> getUnitById(long id){
        try {
            Optional<Unit> fetchedUnit = repository.findById(id);
            Unit unit= fetchedUnit.get();
            return new GenericResponse<>(unit);
        }
        catch (NoSuchElementException e){
            e.printStackTrace();
            return new GenericResponse<>(new Error(0,"Wrong id for Unit","Id : "+id+" does not exist" ));
        }
    }


}

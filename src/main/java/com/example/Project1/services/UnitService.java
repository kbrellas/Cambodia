package com.example.Project1.services;

import com.example.Project1.mappers.UnitMapper;
import com.example.Project1.repositories.UnitRepository;
import com.example.Project1.models.Unit;
import com.example.Project1.models.UnitResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UnitService {

    @Autowired
    private UnitMapper mapper;

    @Autowired
    private UnitRepository repository;

    public List<UnitResponse> getAllUnits(){
        Iterable<Unit> retrievedUnits= repository.findAll();
        List<UnitResponse> units=new ArrayList<>();
        for (Unit unit: retrievedUnits
             ) {
            units.add(mapper.mapUnitToUnitResponse(unit));
        }
        return units;
    }


}

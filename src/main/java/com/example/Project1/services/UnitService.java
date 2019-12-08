package com.example.Project1.services;

import com.example.Project1.mappers.UnitMapper;
import com.example.Project1.models.*;
import com.example.Project1.models.Error;
import com.example.Project1.repositories.UnitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Scope;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.*;

@Service
public class UnitService {

    private UnitMapper mapper;

    private UnitRepository repository;

    public UnitService(UnitMapper mapper, UnitRepository repository) {
        this.mapper = mapper;
        this.repository = repository;
    }

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


    public GenericResponse<UnitResponse> createUnit(Unit unit, Department department) {
        unit.setDepartment(department);
        repository.save(unit);
        return new GenericResponse<>(mapper.mapUnitToUnitResponse(unit));
    }

    public GenericResponse<UnitResponse> updateUnit(Unit partialUnit, long unitId, @Nullable Department department) {
        Optional<Unit> fetchedUnit= repository.findById(unitId);
        if(!fetchedUnit.isPresent()){
            return new GenericResponse<>(new Error(0,"Wrong Input", "Unit with id: "+unitId+" does not exist"));
        }

        Unit retrievedUnit=fetchedUnit.get();

        Map<String, Object> unitMap = new HashMap<>();
        Field[] allFields = partialUnit.getClass().getDeclaredFields();
        for (Field field : allFields) {
            field.setAccessible(true);
            try {

                if(!field.getName().equalsIgnoreCase("id")) {
                    Object value = field.get(partialUnit);
                    if (value != null&& !value.equals(0)) {
                        unitMap.put(field.getName(), value);
                    }
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
                return new GenericResponse<>(new Error(0, "Cannot Access field", "Field : "+field));
            }
            field.setAccessible(false);
        }

        unitMap.forEach((k, v) -> {
            // use reflection to get field k on retrievedEmployee and set it to value k
            Field field = ReflectionUtils.findField(Unit.class, k);
            field.setAccessible(true);
            ReflectionUtils.setField(field, retrievedUnit, v);
            field.setAccessible(false);
        });

        if(department!=null){
            retrievedUnit.setDepartment(department);
        }
        repository.save(retrievedUnit);
        return new GenericResponse<>(mapper.mapUnitToUnitResponse(retrievedUnit));

    }


}

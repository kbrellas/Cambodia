package com.example.Project1.services;

import com.example.Project1.mappers.DepartmentMapper;
import com.example.Project1.models.*;
import com.example.Project1.models.Error;
import com.example.Project1.repositories.BusinessUnitRepository;
import com.example.Project1.repositories.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.*;

@Service
public class DepartmentService {

    private DepartmentMapper mapper;

    private DepartmentRepository repository;

    public DepartmentService(DepartmentMapper mapper, DepartmentRepository repository) {
        this.mapper = mapper;
        this.repository = repository;
    }

    public GenericResponse<List<DepartmentResponse>> getAllDepartments(){
       try {
            Iterable<Department> retrievedDepartments = repository.findAll();
            List<DepartmentResponse> departments = new ArrayList<>();
            for (Department department : retrievedDepartments) {
                departments.add(mapper.mapDepartmentToDepartmentResponse(department));
            }
            if (departments.isEmpty()) {
                return new GenericResponse<>(new Error(0, "Empty Repository", "Please add Department"));
            }
            return new GenericResponse<>(departments);
        } catch(Exception e){
            e.printStackTrace();
            return new GenericResponse<>(new Error(0,"Internal Server Error", "Unable to retrieve data"));
        }
    }

    public GenericResponse<Department> getDepartmentById(long id){
        try {
            Optional<Department> fetchedDepartment = repository.findById(id);
            Department department= fetchedDepartment.get();
            return new GenericResponse<>(department);
        }
        catch (NoSuchElementException e){
            e.printStackTrace();
            return new GenericResponse<>(new Error(0,"Wrong id for Department","Id : "+id+" does not exist" ));
        }
    }

    public GenericResponse<DepartmentResponse> createDepartment(Department department, BusinessUnit businessUnit) {

        department.setBusinessUnit(businessUnit);
        repository.save(department);
        return new GenericResponse<>(mapper.mapDepartmentToDepartmentResponse(department));
    }

    public GenericResponse<DepartmentResponse> updateDepartment(Department partialDepartment,long departmentId,@Nullable BusinessUnit businessUnit) {
        Optional<Department> fetchedDepartment= repository.findById(departmentId);
        if(!fetchedDepartment.isPresent()){
            return new GenericResponse<>(new Error(0,"Wrong Input","Department with id: "+departmentId+" does not exist"));
        }
        Department retrievedDepartment=fetchedDepartment.get();

        Map<String, Object> departmentMap = new HashMap<>();
        Field[] allFields = partialDepartment.getClass().getDeclaredFields();
        for (Field field : allFields) {
            field.setAccessible(true);
            try {

                if(!field.getName().equalsIgnoreCase("id")) {
                    Object value = field.get(partialDepartment);
                    if (value != null&& !value.equals(0)) {
                        departmentMap.put(field.getName(), value);
                    }
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
                return new GenericResponse<>(new Error(0, "Cannot Access field", "Field : "+field));
            }
            field.setAccessible(false);
        }

        departmentMap.forEach((k, v) -> {
            // use reflection to get field k on retrievedEmployee and set it to value k
            Field field = ReflectionUtils.findField(Department.class, k);
            field.setAccessible(true);
            ReflectionUtils.setField(field, retrievedDepartment, v);
            field.setAccessible(false);
        });

        if(businessUnit!=null){
            retrievedDepartment.setBusinessUnit(businessUnit);

        }
        repository.save(retrievedDepartment);
        return new GenericResponse<>(mapper.mapDepartmentToDepartmentResponse(retrievedDepartment));
    }


}


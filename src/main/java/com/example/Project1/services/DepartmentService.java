package com.example.Project1.services;

import com.example.Project1.mappers.DepartmentMapper;
import com.example.Project1.models.GenericResponse;
import com.example.Project1.repositories.BusinessUnitRepository;
import com.example.Project1.repositories.DepartmentRepository;
import com.example.Project1.models.Department;
import com.example.Project1.models.DepartmentResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.Project1.models.Error;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class DepartmentService {

    @Autowired
    private DepartmentMapper mapper;

    @Autowired
    private DepartmentRepository repository;

    @Autowired
    private BusinessUnitRepository businessUnitRepository;

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
}


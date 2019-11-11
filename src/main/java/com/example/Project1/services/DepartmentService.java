package com.example.Project1.services;

import com.example.Project1.mappers.DepartmentMapper;
import com.example.Project1.repositories.DepartmentRepository;
import com.example.Project1.models.Department;
import com.example.Project1.models.DepartmentResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DepartmentService {

    @Autowired
    private DepartmentMapper mapper;

    @Autowired
    private DepartmentRepository repository;

    public List<DepartmentResponse> getAllDepartments(){
        Iterable<Department> retrievedDepartments= repository.findAll();
        List<DepartmentResponse> departments = new ArrayList<>();
        for (Department department: retrievedDepartments
             ) {
            departments.add(mapper.mapDepartmentToDepartmentResponse(department));
        }
        return departments;
    }


}

package com.example.Project1.services;

import com.example.Project1.mappers.EmployeeMapper;
import com.example.Project1.implementations.SearchEmployeeStrategy;
import com.example.Project1.models.EmployeeResponse;
import com.example.Project1.models.Employee;
import com.example.Project1.implementations.SearchEmployeeStrategyFactory;
import com.example.Project1.repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeMapper mapper;

    @Autowired
    private EmployeeRepository repository;

    @Autowired
    private SearchEmployeeStrategyFactory factory;

    public List<EmployeeResponse> getAllEmployees(){
        Iterable<Employee> retrievedEmployees= repository.findAll();
        List<EmployeeResponse> employees= new ArrayList<>();
        for (Employee employee: retrievedEmployees
             ) {
            employees.add(mapper.mapEmployeeToEmployeeResponse(employee));
        }
        return employees;
    }


    public List<EmployeeResponse> getEmployeeBySearchCriteria(String searchCriteria, Long searchId) {
        Iterable<Employee> retrievedEmployees= repository.findAll();
        List<EmployeeResponse> employees= new ArrayList<>();
        SearchEmployeeStrategy strategy= factory.makeStrategyForCriteria(searchCriteria);
        employees=mapper.mapAllEmployees(strategy.execute(searchId, retrievedEmployees));

        return employees;
    }
}

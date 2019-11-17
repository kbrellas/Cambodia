package com.example.Project1.services;

import com.example.Project1.mappers.EmployeeMapper;
import com.example.Project1.implementations.SearchEmployeeStrategy;
import com.example.Project1.models.*;
import com.example.Project1.implementations.SearchEmployeeStrategyFactory;
import com.example.Project1.models.Error;
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

    public GenericResponse<List<EmployeeResponse>> getAllEmployees() {
        try {
            Iterable<Employee> retrievedEmployees = repository.findAll();
            List<EmployeeResponse> employees = new ArrayList<>();
            for (Employee employee : retrievedEmployees
            ) {
                employees.add(mapper.mapEmployeeToEmployeeResponse(employee));
            }
            if (employees.isEmpty()) {
                return new GenericResponse<>(new Error(0, "Empty Repository", "Please add Employees"));
            }
            return new GenericResponse<>(employees);
        }
        catch(Exception e){
            e.printStackTrace();
            return new GenericResponse<>(new Error(0,"Internal Server Error", "Unable to retrieve data"));
        }

    }


    public GenericResponse<List<EmployeeResponse>> getEmployeeBySearchCriteria(String searchCriteria, Long searchId) {
        Iterable<Employee> retrievedEmployees = repository.findAll();
        SearchEmployeeStrategy strategy = factory.makeStrategyForCriteria(searchCriteria);
        GenericResponse<List<Employee>> response  = strategy.execute(searchId, retrievedEmployees);
        if(response.getError()!=null){
            return new GenericResponse<>(response.getError());
        }
        List<EmployeeResponse> employees= mapper.mapAllEmployees(response.getData());
        return new GenericResponse<>(employees);
    }

    public GenericResponse<Employee> createNewEmployee(Employee employee, Unit unit) {
        employee.setUnit(unit);
        if(employee.getHireDate()==null){
            return new GenericResponse<>(new Error(0,"HireDate input Error","Hire Date cannot be null"));
        }
        if(employee.getLeaveDate()!=null&&employee.getLeaveDate().isBefore(employee.getHireDate())){
            return new GenericResponse<>(new Error(0,"Date input Error","Dismiss Date cannot be before Hire Date"));
        }

        repository.save(employee);
        return new GenericResponse<>(employee);
    }
}

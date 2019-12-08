package com.example.Project1.services;

import com.example.Project1.mappers.EmployeeMapper;
import com.example.Project1.implementations.SearchEmployeeStrategy;
import com.example.Project1.models.*;
import com.example.Project1.implementations.SearchEmployeeStrategyFactory;
import com.example.Project1.models.Error;
import com.example.Project1.repositories.EmployeeRepository;
import com.example.Project1.repositories.TaskRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.lang.reflect.Field;
import java.time.LocalDate;
import java.util.*;

@Service
public class EmployeeService {


    private EmployeeMapper mapper;


    private EmployeeRepository repository;


    private SearchEmployeeStrategyFactory factory;

    @Autowired
    private EntityManager manager;

    public EmployeeService(EmployeeMapper mapper, EmployeeRepository repository, SearchEmployeeStrategyFactory factory) {
        this.mapper = mapper;
        this.repository = repository;
        this.factory = factory;
    }

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
        } catch (Exception e) {
            e.printStackTrace();
            return new GenericResponse<>(new Error(0, "Internal Server Error", "Unable to retrieve data"));
        }

    }


    public GenericResponse<List<EmployeeResponse>> getEmployeeBySearchCriteria(String searchCriteria, Long searchId) {
        Iterable<Employee> retrievedEmployees = repository.findAll();
        SearchEmployeeStrategy strategy = factory.makeStrategyForCriteria(searchCriteria);
        GenericResponse<List<Employee>> response = strategy.execute(searchId, retrievedEmployees);
        if (response.getError() != null) {
            return new GenericResponse<>(response.getError());
        }
        List<EmployeeResponse> employees = mapper.mapAllEmployees(response.getData());
        return new GenericResponse<>(employees);
    }

    public GenericResponse<Employee> createNewEmployee(Employee employee, Unit unit) {
        employee.setUnit(unit);
        if (employee.getHireDate() == null) {
            return new GenericResponse<>(new Error(0, "HireDate input Error", "Hire Date cannot be null"));
        }
        if (employee.getLeaveDate() != null && employee.getLeaveDate().isBefore(employee.getHireDate())) {
            return new GenericResponse<>(new Error(0, "Date input Error", "Dismiss Date cannot be before Hire Date"));
        }

        repository.save(employee);
        return new GenericResponse<>(employee);
    }

    public GenericResponse<Employee> updateEmployee(Employee partialEmployee, long id, @Nullable Unit unit) {
        //extract changed fields from patch body
        Map<String, Object> employeeMap = new HashMap<>();
        Field[] allFields = partialEmployee.getClass().getDeclaredFields();
        for (Field field : allFields) {
            field.setAccessible(true);
            try {

                if(!field.getName().equalsIgnoreCase("id")) {
                    Object value = field.get(partialEmployee);
                    if (value != null&& !value.equals(0)) {
                        employeeMap.put(field.getName(), value);
                    }
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
                return new GenericResponse<>(new Error(0, "Cannot Access field", "Field : "+field));
            }
            field.setAccessible(false);
        }


        //check if both dates changed in a valid manner
        if (partialEmployee.getLeaveDate() != null && partialEmployee.getHireDate() != null && partialEmployee.getLeaveDate().isBefore(partialEmployee.getHireDate())) {
            return new GenericResponse<>(new Error(0, "Wrong Date input", "Leave Date cannot be before Hire Date"));
        }

        //apply changed fields to actual employee
        Optional<Employee> fetchedEmployee = repository.findById(id);
        if(!fetchedEmployee.isPresent()){
            return new GenericResponse<>(new Error(0,"Wrong Employee id","Employee with id: "+id+" does not exist"));
        }
        Employee retrievedEmployee=fetchedEmployee.get();

        // Map key is field name, v is value
        employeeMap.forEach((k, v) -> {
            // use reflection to get field k on retrievedEmployee and set it to value k
            Field field = ReflectionUtils.findField(Employee.class, k);
            field.setAccessible(true);
            ReflectionUtils.setField(field, retrievedEmployee, v);
            field.setAccessible(false);
        });
        if(unit!=null){
            retrievedEmployee.setUnit(unit);
        }
        repository.save(retrievedEmployee);
        return new GenericResponse<>(retrievedEmployee);
    }

    public GenericResponse<List<EmployeeResponse>> getSpecificEmployees(List<Employee> employees){
        if(employees.isEmpty()){
            return new GenericResponse<>(new Error(0,"No Employees found","The task has no Employees"));
        }
        return new GenericResponse<>(mapper.mapAllEmployees(employees));

    }

    public GenericResponse<Employee> getEmployeeById(long id){
        Optional<Employee> fetchedEmployee = repository.findById(id);
        if(!fetchedEmployee.isPresent()){
            return new GenericResponse<>(new Error(0,"Wrong input error","Employee with id : "+id+" does not exist."));
        }
        return new GenericResponse<>(fetchedEmployee.get());
    }

}

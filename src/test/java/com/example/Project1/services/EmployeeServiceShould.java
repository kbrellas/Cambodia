package com.example.Project1.services;

import com.example.Project1.implementations.SearchEmployeeByBusinessUnitStrategy;
import com.example.Project1.implementations.SearchEmployeeStrategyFactory;
import com.example.Project1.mappers.BusinessUnitMapper;
import com.example.Project1.mappers.EmployeeMapper;
import com.example.Project1.models.*;
import com.example.Project1.models.Error;
import com.example.Project1.repositories.BusinessUnitRepository;
import com.example.Project1.repositories.EmployeeRepository;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

public class EmployeeServiceShould {
    private EmployeeService service;
    private Employee employee;
    private Company company;
    private BusinessUnit businessUnit;
    private Department department;
    private Unit unit;
    private Employee wrongEmployee;
    private Employee wrongEmployee2;

    @Mock
    private SearchEmployeeStrategyFactory factory;
    @Mock
    private EmployeeRepository repository;
    @Mock
    private EmployeeMapper mapper;

    private Iterable<Employee> mockedEmployees;
    @Mock
    private BusinessUnitRepository businessUnitRepository;


    @Before
    public void setup() {

        MockitoAnnotations.initMocks(this);
        company = new Company(1, "Unisystems");
        businessUnit = new BusinessUnit(1, "Business Unit 1", company);
        department = new Department(1, "HR", businessUnit);
        unit = new Unit(1, "Unit 1", department);
        employee = new Employee(1, 12345, "Brown", "James", "Main St. 134", "6965455545", LocalDate.of(2018, 5, 2), LocalDate.of(2019, 2, 23), Status.INACTIVE, ContractType.UNISYSTEMS, unit, "Department Manager");

        mockedEmployees = new ArrayList<Employee>() {
            {
                add(new Employee(1, 12345, "Brown", "James", "Main St. 134", "6965455545", LocalDate.of(2018, 5, 2), LocalDate.of(2019, 2, 23), Status.INACTIVE, ContractType.UNISYSTEMS, unit, "Department Manager"));
                add(new Employee(2, 12345, "White", "James", "Main St. 134", "6965455545", LocalDate.of(2018, 5, 2), LocalDate.of(2019, 2, 23), Status.INACTIVE, ContractType.UNISYSTEMS, unit, "Department Manager"));
                add(new Employee(3, 12345, "Brown", "James", "Main St. 134", "6965455545", LocalDate.of(2018, 5, 2), LocalDate.of(2019, 2, 23), Status.INACTIVE, ContractType.UNISYSTEMS, unit, "Department Manager"));

            }
        };
        when(repository.findAll()).thenReturn(mockedEmployees);
        when(repository.findById((long) 1)).thenReturn(java.util.Optional.of(new Employee(1, 12345, "Brown", "James", "Main St. 134", "6965455545", LocalDate.of(2018, 5, 2), LocalDate.of(2019, 2, 23), Status.INACTIVE, ContractType.UNISYSTEMS, unit, "Department Manager")));
        when(repository.save(any(Employee.class))).thenReturn(employee);
        when(mapper.mapEmployeeToEmployeeResponse(any())).thenReturn(new EmployeeResponse(1, 13243, "James Brown", "6965455545", "02/05/2018-23/02/2019", "inactive", "unisystems", "Department Manager", unit.getName()));
        List<EmployeeResponse> employeeResponseList=new ArrayList<>();
        employeeResponseList.add(new EmployeeResponse(1, 13243, "James Brown", "6965455545", "02/05/2018-23/02/2019", "inactive", "unisystems", "Department Manager", unit.getName()));
        employeeResponseList.add(new EmployeeResponse(1, 13243, "James Brown", "6965455545", "02/05/2018-23/02/2019", "inactive", "unisystems", "Department Manager", unit.getName()));
        employeeResponseList.add(new EmployeeResponse(1, 13243, "James Brown", "6965455545", "02/05/2018-23/02/2019", "inactive", "unisystems", "Department Manager", unit.getName()));

        when(mapper.mapAllEmployees(any())).thenReturn(employeeResponseList);
        when(businessUnitRepository.findById((long)1)).thenReturn(java.util.Optional.of(businessUnit));
        when(factory.makeStrategyForCriteria(ArgumentMatchers.any())).thenReturn(new SearchEmployeeByBusinessUnitStrategy(new BusinessUnitService(new BusinessUnitMapper(), businessUnitRepository)));
        service = new EmployeeService(mapper, repository, factory);

        wrongEmployee= new Employee(1, 12345, "Brown", "James", "Main St. 134", "6965455545", null, LocalDate.of(2019, 2, 23), Status.INACTIVE, ContractType.UNISYSTEMS, unit, "Department Manager");
        wrongEmployee2= new Employee(1, 12345, "Brown", "James", "Main St. 134", "6965455545", LocalDate.of(2019,7,21), LocalDate.of(2019, 2, 23), Status.INACTIVE, ContractType.UNISYSTEMS, unit, "Department Manager");

    }

    @Test
    public void retrieveEmployeesFromRepository() {
        service.getAllEmployees();
        Mockito.verify(repository).findAll();
    }

    @Test
    public void retrieveEmployeeFromRepository() {
        service.getEmployeeById(1);
        Mockito.verify(repository).findById((long) 1);
    }

    @Test
    public void usesEmployeeMapper() {
        service.getAllEmployees();
        // ArgumentMatchers.any() gia na elenxo an kalesthke me opoiadhpote sunarthsh
        Mockito.verify(mapper, times(3)).mapEmployeeToEmployeeResponse(any());
    }

    @Test
    public void returnListOfEmployeeResponse(){
        GenericResponse<List<EmployeeResponse>> output = service.getAllEmployees();
        Assert.assertEquals(3,output.getData().size());
        List<EmployeeResponse> expectedList=new ArrayList<>();
        expectedList.add(new EmployeeResponse(1, 13243, "James Brown", "6965455545", "02/05/2018-23/02/2019", "inactive", "unisystems", "Department Manager", unit.getName()));
        expectedList.add(new EmployeeResponse(1, 13243, "James Brown", "6965455545", "02/05/2018-23/02/2019", "inactive", "unisystems", "Department Manager", unit.getName()));
        expectedList.add(new EmployeeResponse(1, 13243, "James Brown", "6965455545", "02/05/2018-23/02/2019", "inactive", "unisystems", "Department Manager", unit.getName()));
        Assert.assertThat(expectedList, Matchers.samePropertyValuesAs(output.getData()));
        Assert.assertThat(expectedList.get(0),Matchers.samePropertyValuesAs(output.getData().get(0)));
        Assert.assertThat(expectedList.get(1),Matchers.samePropertyValuesAs(output.getData().get(1)));
        Assert.assertThat(expectedList.get(2),Matchers.samePropertyValuesAs(output.getData().get(2)));
    }


    @Test
    public void returnEmployeeById(){
        GenericResponse<Employee> output=service.getEmployeeById((long)1);
        Assert.assertThat(employee,Matchers.samePropertyValuesAs(output.getData()));

    }

    @Test
    public void createEmployeeToRepository(){
        GenericResponse<Employee> output= service.createNewEmployee(employee,unit);
        Mockito.verify(repository).save(employee);
        Assert.assertThat(employee,Matchers.samePropertyValuesAs(output.getData()));
    }

    @Test
    public void returnErrorWhenWrongEmployeeTriesToBeCreated(){
        Error error1= new Error(0, "HireDate input Error", "Hire Date cannot be null");
        Error error2= new Error(0, "Date input Error", "Dismiss Date cannot be before Hire Date");
        GenericResponse<Employee> output1= service.createNewEmployee(wrongEmployee,unit);
        GenericResponse<Employee> output2=service.createNewEmployee(wrongEmployee2,unit);
        Assert.assertThat(error1,Matchers.samePropertyValuesAs(output1.getError()));
        Assert.assertThat(error2,Matchers.samePropertyValuesAs(output2.getError()));
    }

    @Test
    public void updateEmployeeToRepository(){
        GenericResponse<Employee> output= service.updateEmployee(employee,1,unit);
        Mockito.verify(repository).findById((long)1);
        Mockito.verify(repository).save(any(Employee.class));

        Assert.assertThat(employee,Matchers.samePropertyValuesAs(output.getData()));
    }

    @Test
    public void returnErrorWhenTriesToUpdateWrongEmployee(){
        Error error1= new Error(0, "Wrong Date input", "Leave Date cannot be before Hire Date");
        Error error2= new Error(0,"Wrong Employee id","Employee with id: "+10+" does not exist");
       GenericResponse<Employee> output1= service.updateEmployee(wrongEmployee2,1,unit);
       GenericResponse<Employee> output2= service.updateEmployee(employee,10,unit);
       Assert.assertThat(error1,Matchers.samePropertyValuesAs(output1.getError()));
       Assert.assertThat(error2,Matchers.samePropertyValuesAs(output2.getError()));
    }

    @Test
    public void returnSpecificEmployeeResponses(){
        List<Employee> employees= new ArrayList<>();
        for (Employee employee: mockedEmployees
             ) {
            employees.add(employee);
        }

        GenericResponse<List<EmployeeResponse>> output= service.getSpecificEmployees(employees);

        List<EmployeeResponse> expectedList= new ArrayList<>();
        expectedList.add(new EmployeeResponse(1, 13243, "James Brown", "6965455545", "02/05/2018-23/02/2019", "inactive", "unisystems", "Department Manager", unit.getName()));
        expectedList.add(new EmployeeResponse(1, 13243, "James Brown", "6965455545", "02/05/2018-23/02/2019", "inactive", "unisystems", "Department Manager", unit.getName()));
        expectedList.add(new EmployeeResponse(1, 13243, "James Brown", "6965455545", "02/05/2018-23/02/2019", "inactive", "unisystems", "Department Manager", unit.getName()));
        Assert.assertEquals(3,output.getData().size());
        Assert.assertThat(expectedList, Matchers.samePropertyValuesAs(output.getData()));
        Assert.assertThat(expectedList.get(0),Matchers.samePropertyValuesAs(output.getData().get(0)));
        Assert.assertThat(expectedList.get(1),Matchers.samePropertyValuesAs(output.getData().get(1)));
        Assert.assertThat(expectedList.get(2),Matchers.samePropertyValuesAs(output.getData().get(2)));
    }

    @Test
    public void returnListOfEmployeeResponseByCriteria(){
        GenericResponse<List<EmployeeResponse>> output=service.getEmployeeBySearchCriteria("businessunit",(long)1);
        List<EmployeeResponse> expectedList=new ArrayList<>();
        expectedList.add(new EmployeeResponse(1, 13243, "James Brown", "6965455545", "02/05/2018-23/02/2019", "inactive", "unisystems", "Department Manager", unit.getName()));
        expectedList.add(new EmployeeResponse(1, 13243, "James Brown", "6965455545", "02/05/2018-23/02/2019", "inactive", "unisystems", "Department Manager", unit.getName()));
        expectedList.add(new EmployeeResponse(1, 13243, "James Brown", "6965455545", "02/05/2018-23/02/2019", "inactive", "unisystems", "Department Manager", unit.getName()));
        Assert.assertEquals(3,output.getData().size());
        Assert.assertThat(expectedList,Matchers.samePropertyValuesAs(output.getData()));
        Assert.assertThat(expectedList.get(0),Matchers.samePropertyValuesAs(output.getData().get(0)));
        Assert.assertThat(expectedList.get(1),Matchers.samePropertyValuesAs(output.getData().get(1)));
        Assert.assertThat(expectedList.get(2),Matchers.samePropertyValuesAs(output.getData().get(2)));

    }

}


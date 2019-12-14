package com.example.Project1.mappers;

import com.example.Project1.models.*;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.samePropertyValuesAs;

public class TaskMapperShould {
    private TaskMapper mapper;
    private Task taskInput1,taskInput2;
    private TaskResponse taskResponseOutput1,taskResponseOutput2;
    private List<Task> inputTaskList=new ArrayList<>();
    private List<TaskResponse> outputTaskResponseList =new ArrayList<>();
    private FullTaskInfoResponse fullTaskInfoResponse1;
    private FullTaskInfoResponse fullTaskInfoResponse2;
    private List<EmployeeResponse> employeeResponseList=new ArrayList<>();
    @Before
    public void setup(){
        mapper=new TaskMapper();
        EmployeeMapper employeeMapper=new EmployeeMapper();
        Company company = new Company("Company");
        company.setId(1);
        BusinessUnit businessUnit1 = new BusinessUnit("Business Unit 1");
        BusinessUnit businessUnit2 = new BusinessUnit("Business Unit 2");
        businessUnit1.setId(2);
        businessUnit2.setId(3);
        businessUnit1.setCompany(company);
        businessUnit2.setCompany(company);
        Department department1 = new Department("D1");
        Department department2 = new Department("D2");
        department1.setBusinessUnit(businessUnit1);
        department2.setBusinessUnit(businessUnit2);
        department1.setId(4);
        department2.setId(5);
        Unit unit1 = new Unit("Unit 1");
        Unit unit2 = new Unit("Unit 2");
        unit1.setId(6);
        unit2.setId(7);
        unit1.setDepartment(department1);
        unit2.setDepartment(department2);
        Employee employee1 = new Employee(12345, "Name A", "Name B", "Street A", "123456789", LocalDate.of(2018, 5, 2), LocalDate.of(2019, 2, 23), Status.INACTIVE, ContractType.UNISYSTEMS, unit1, "Department Manager");
        Employee employee2 = new Employee(53532, "Name C", "Name D", "Second St. 75", "987654321", LocalDate.of(2015, 2, 13), null, Status.ACTIVE, ContractType.EXTERNAL, unit2, "HR Manager");
        EmployeeResponse employeeResponse1=employeeMapper.mapEmployeeToEmployeeResponse(employee1);
        EmployeeResponse employeeResponse2=employeeMapper.mapEmployeeToEmployeeResponse(employee2);
        List<Employee> employeeList=new ArrayList<>();
        employeeList.add(employee1);
        employeeList.add(employee2);

        employeeResponseList.add(employeeResponse1);
        employeeResponseList.add(employeeResponse2);
        List<String> updates=new ArrayList<>();
        updates.add("update A");
        updates.add("update B");
        taskInput1 = new Task("Task1","Task A",4,5,1,TaskStatus.NEW,updates,employeeList);
        taskInput2 = new Task("Task2","Task B",2,1,3,TaskStatus.NEW,null,null);
        taskInput1.setId(8);
        taskInput2.setId(9);
        fullTaskInfoResponse1=new FullTaskInfoResponse(8,"Task1","Task A",Difficulty.MEDIUM,TaskStatus.NEW,employeeResponseList,updates);
        fullTaskInfoResponse2=new FullTaskInfoResponse(9,"Task2","Task B",Difficulty.MEDIUM,TaskStatus.NEW,null,null);
        inputTaskList.add(taskInput1);
        inputTaskList.add(taskInput2);

    }
    @Test
    public void keepSameId(){
        Assert.assertEquals(taskInput1.getId(),mapper.mapTaskToTaskResponse(taskInput1).getId());
        Assert.assertEquals(taskInput2.getId(),mapper.mapTaskToTaskResponse(taskInput2).getId());
    }

    @Test
    public void keepSameTitle(){
        Assert.assertEquals(taskInput1.getTitle(),mapper.mapTaskToTaskResponse(taskInput1).getTitle());
        Assert.assertEquals(taskInput2.getTitle(),mapper.mapTaskToTaskResponse(taskInput2).getTitle());
    }

    @Test
    public void keepSameDescription(){
        Assert.assertEquals(taskInput1.getDescription(),mapper.mapTaskToTaskResponse(taskInput1).getDesc());
        Assert.assertEquals(taskInput2.getDescription(),mapper.mapTaskToTaskResponse(taskInput2).getDesc());
    }

    @Test
    public void mapFullInfoReturnsFullTaskInfoResponse(){

      Assert.assertThat(fullTaskInfoResponse1, samePropertyValuesAs(mapper.mapFullTaskInfoResponse(taskInput1,employeeResponseList)));
      Assert.assertThat(fullTaskInfoResponse2, samePropertyValuesAs(mapper.mapFullTaskInfoResponse(taskInput2,null)));

    }


}

package com.example.Project1.mappers;

import com.example.Project1.mappers.EmployeeMapper;
import com.example.Project1.models.*;
import org.hamcrest.CoreMatchers;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.swing.table.TableRowSorter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.containsInAnyOrder;

public class EmployeeMapperShould {

    private EmployeeMapper mapper;
    private Employee input;
    private Employee input2;
    private EmployeeResponse output;
    private EmployeeResponse output2;
    private Unit unit;
    private Department department;
    private BusinessUnit businessUnit;
    private Company company;
    private List<Employee> inputList=new ArrayList<>();
    private List<EmployeeResponse> expectedOutputList= new ArrayList<>();
    private EmployeeResponse expectedOutput1;
    private EmployeeResponse expectedOutput2;


    @Before
    public void setup(){

        mapper=new EmployeeMapper();
        company= new Company("UniSystems");
        company.setId(1);
        businessUnit = new BusinessUnit("Business Unit 1");
        businessUnit.setId(1);
        businessUnit.setCompany(company);
        department= new Department("HR");
        department.setId(1);
        department.setBusinessUnit(businessUnit);
        unit=new Unit("Unit 1");
        unit.setId(1);
        unit.setDepartment(department);
        input=new Employee(234,"Sparrow","Jack", "Main Av. 2","4353637586", LocalDate.of(2015,3,13),null,Status.ACTIVE,ContractType.UNISYSTEMS,unit,"Manager");
        input.setId(1);
        input2=new Employee(238,"Spar","Jacker", "Main Av. 1","4353637585", LocalDate.of(2015,3,13),LocalDate.of(2019,2,11),Status.INACTIVE,ContractType.EXTERNAL,unit,"Manager");
        input2.setId(2);
        inputList.add(input);
        inputList.add(input2);

        output = mapper.mapEmployeeToEmployeeResponse(input);
        output2 =mapper.mapEmployeeToEmployeeResponse(input2);

        expectedOutput1= new EmployeeResponse(1,234,"Jack Sparrow", "4353637586","13/03/2015-present", "active","unisystems","Manager",unit.getName());
        expectedOutput2= new EmployeeResponse(2,238,"Jacker Spar", "4353637585","13/03/2015-11/02/2019", "inactive","external","Manager",unit.getName());

        expectedOutputList.add(expectedOutput1);
        expectedOutputList.add(expectedOutput2);

    }

    @Test
    public void keepSameId(){

        Assert.assertEquals(1, output.getId());
    }

    @Test
    public void keepSameRecordNumber(){

        Assert.assertEquals(234, output.getRecordNumber());
    }

    @Test
    public void mergeFirstNameAndLastName(){

        Assert.assertEquals((input.getFirstName()+" "+input.getLastName()),output.getFullName());
    }

    @Test
    public void keepSameTelephoneNumber(){

        Assert.assertEquals(input.getTelephoneNo(),output.getTelephone());
    }

    @Test
    public void calculateWorkingPeriodWhenActive(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/YYYY");

        String hireDate=formatter.format(input.getHireDate()) +"-present";

        Assert.assertEquals(hireDate,output.getWorkingPeriod());
    }

    @Test
    public void calculateWorkingPeriodWhenNotActive(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/YYYY");
        String hireDate=formatter.format(input2.getHireDate());
        String leaveDate=formatter.format(input2.getLeaveDate());

        Assert.assertEquals(hireDate+"-"+leaveDate,output2.getWorkingPeriod());
    }

    @Test
    public void showActiveStatusWhenActive(){
        Assert.assertEquals("active",output.getStatus());
    }

    @Test
    public void showInactiveStatusWhenInactive(){
        Assert.assertEquals("inactive",output2.getStatus());
    }

    @Test
    public void keepSameContractType(){
        Assert.assertEquals(input.getContractType().toString().toLowerCase(),output.getContractType());
    }

    @Test
    public void keepSamePosition(){
        Assert.assertEquals(input.getPosition(),output.getPosition());
    }

    @Test
    public void keepSameUnitName(){
        Assert.assertEquals(input.getUnitName(),output.getUnitName());
    }

    @Test
    public void returnEmployeeResponseListWhenGivenEmployeeList(){
        List<EmployeeResponse> outputMapper= mapper.mapAllEmployees(inputList);

        Assert.assertThat(outputMapper.get(0), Matchers.samePropertyValuesAs(expectedOutputList.get(0)));
        Assert.assertThat(outputMapper.get(1), Matchers.samePropertyValuesAs(expectedOutputList.get(1)));

    }

}

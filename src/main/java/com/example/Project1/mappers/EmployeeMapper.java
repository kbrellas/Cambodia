package com.example.Project1.mappers;

import com.example.Project1.models.EmployeeResponse;
import com.example.Project1.models.Employee;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
public class EmployeeMapper {

    public List<EmployeeResponse> mapAllEmployees(List<Employee> allEmployees){
        List<EmployeeResponse> response= new ArrayList<>();
        for (Employee e: allEmployees
             ) {
            response.add(mapEmployeeToEmployeeResponse(e));

        }
        return response;
    }

    public EmployeeResponse mapEmployeeToEmployeeResponse(Employee employee) {
        return new EmployeeResponse(
                employee.getId(),
                employee.getRecordNumber(),
                mapFullName(employee),
                employee.getTelephoneNo(),
                mapWorkingPeriod(employee.getHireDate(),employee.getLeaveDate()),
                mapStatus(employee),
                employee.getContractType(),
                employee.getPosition(),
                employee.getUnitName()
        );
    }

    private String mapStatus(Employee employee) {
        if(employee.isActive()){
            return "active";
        }
        else{
            return "inactive";
        }
    }

    private String mapFullName(Employee employee) {
        return employee.getFirstName()+" "+employee.getLastName();
    }

    private String mapWorkingPeriod(LocalDate hireDate, LocalDate leaveDate){
        if(leaveDate==null){
            return hireDate.toString().replace("-","/")+"- present";
        }
        else{
            return hireDate.toString().replace("-","/")+"-"+leaveDate.toString().replace("-","/");
        }

    }
}

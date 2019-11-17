package com.example.Project1.mappers;

import com.example.Project1.models.EmployeeResponse;
import com.example.Project1.models.Employee;
import org.springframework.stereotype.Component;

import java.time.format.DateTimeFormatter;
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
                mapWorkingPeriod(employee),
                mapStatus(employee),
                mapContractType(employee),
                employee.getPosition(),
                employee.getUnitName()
        );
    }

    private String mapContractType(Employee employee) {
        return employee.getContractType().toString().toLowerCase();
    }

    private String mapStatus(Employee employee) {
        return employee.getStatus().toString().toLowerCase();
    }

    private String mapFullName(Employee employee) {
        return employee.getFirstName()+" "+employee.getLastName();
    }

    private String mapWorkingPeriod (Employee emp) {

        return hiredDateResponse(emp) + "-"+ leaveDateResponse(emp);
    }

    private String leaveDateResponse(Employee emp) {

        if (emp.getLeaveDate() == null){
            return "present";

        }
        else {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/YYYY");
            return formatter.format(emp.getLeaveDate());
        }
    }

    private String hiredDateResponse (Employee employee){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(("dd/MM/YYYY"));
        return formatter.format(employee.getHireDate());
    }

}

package com.example.Project1.implementations;

import org.springframework.stereotype.Component;

@Component
public class SearchEmployeeStrategyFactory {

    public SearchEmployeeStrategy makeStrategyForCriteria(String criteria){
        switch (criteria.toLowerCase()){
            case "unit":
                return new SearchEmployeeByUnitStrategy();

            case "department":
                return new SearchEmployeeByDepartmentStrategy();
            case "businessunit":
                return new SearchEmployeeByBusinessUnitStrategy();
            case "company":
                return new SearchEmployeeByCompanyStrategy();
            default:
                return new SearchEmployeeWrongInput();
        }
    }
}

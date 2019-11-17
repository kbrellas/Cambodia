package com.example.Project1.implementations;
import com.example.Project1.services.BusinessUnitService;
import com.example.Project1.services.CompanyService;
import com.example.Project1.services.DepartmentService;
import com.example.Project1.services.UnitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SearchEmployeeStrategyFactory {

    @Autowired
    private UnitService unitService;

    @Autowired
    private DepartmentService departmentService;

    @Autowired
    private BusinessUnitService businessUnitService;

    @Autowired
    private CompanyService companyService;


    public SearchEmployeeStrategy makeStrategyForCriteria(String criteria){
        switch (criteria.toLowerCase()){
            case "unit":
                return new SearchEmployeeByUnitStrategy(unitService);

            case "department":
                return new SearchEmployeeByDepartmentStrategy(departmentService);
            case "businessunit":
                return new SearchEmployeeByBusinessUnitStrategy(businessUnitService);
            case "company":
                return new SearchEmployeeByCompanyStrategy(companyService);
            default:
                return new SearchEmployeeWrongInput();
        }
    }
}

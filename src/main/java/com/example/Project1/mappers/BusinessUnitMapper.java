package com.example.Project1.mappers;

import com.example.Project1.models.BusinessUnit;
import com.example.Project1.models.BusinessUnitResponse;
import org.springframework.stereotype.Component;

@Component
public class BusinessUnitMapper {
    public BusinessUnitResponse mapBusinessUnitToBusinessUnitResponse(BusinessUnit businessUnit) {
        return new BusinessUnitResponse(
                businessUnit.getId(),
                businessUnit.getName(),
                mapCompanyName(businessUnit)
        );
    }

    private String mapCompanyName(BusinessUnit businessUnit) {
        return businessUnit.getCompany().getName();
    }
}

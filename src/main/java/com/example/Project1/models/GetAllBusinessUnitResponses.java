package com.example.Project1.models;

import java.util.List;

public class GetAllBusinessUnitResponses {
    private List<BusinessUnitResponse> businessUnits;

    public GetAllBusinessUnitResponses(List<BusinessUnitResponse> businessUnits) {
        this.businessUnits = businessUnits;
    }

    public List<BusinessUnitResponse> getBusinessUnits() {
        return businessUnits;
    }

    public void setBusinessUnits(List<BusinessUnitResponse> businessUnits) {
        this.businessUnits = businessUnits;
    }
}

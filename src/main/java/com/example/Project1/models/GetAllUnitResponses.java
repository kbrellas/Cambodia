package com.example.Project1.models;

import java.util.List;

public class GetAllUnitResponses {
    private List<UnitResponse> units;

    public GetAllUnitResponses(List<UnitResponse> units) {
        this.units = units;
    }

    public List<UnitResponse> getUnits() {
        return units;
    }

    public void setUnits(List<UnitResponse> units) {
        this.units = units;
    }
}

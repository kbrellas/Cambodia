package com.example.Project1.models;

import java.util.List;

public class GetAllCompanyResponses {
    private List<CompanyResponse> companies;

    public GetAllCompanyResponses(List<CompanyResponse> companies) {
        this.companies = companies;
    }

    public List<CompanyResponse> getCompanies() {
        return companies;
    }

    public void setCompanies(List<CompanyResponse> companies) {
        this.companies = companies;
    }
}

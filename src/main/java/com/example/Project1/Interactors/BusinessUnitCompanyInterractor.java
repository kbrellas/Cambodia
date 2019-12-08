package com.example.Project1.Interactors;

import com.example.Project1.models.BusinessUnit;
import com.example.Project1.models.BusinessUnitResponse;
import com.example.Project1.models.Company;
import com.example.Project1.models.Error;
import com.example.Project1.models.GenericResponse;
import com.example.Project1.repositories.BusinessUnitRepository;
import com.example.Project1.repositories.CompanyRepository;
import com.example.Project1.services.BusinessUnitService;
import com.example.Project1.services.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BusinessUnitCompanyInterractor {

    @Autowired
    private CompanyService companyService;

    @Autowired
    private BusinessUnitService businessUnitService;


    public GenericResponse<BusinessUnitResponse> createBusinessUnit(BusinessUnit businessUnit) {
        if(businessUnit.getCompany()!=null) {
            GenericResponse<Company> companyResponse = companyService.getCompanyById(businessUnit.getCompany().getId());
            if (companyResponse.getError() != null) {
                return new GenericResponse<>(companyResponse.getError());
            }
            return businessUnitService.createBusinessUnit(businessUnit, companyResponse.getData());
        }
        return new GenericResponse<>(new Error(0,"Wrong input","Company arguement missing"));
    }

    public GenericResponse<BusinessUnitResponse> updateBusinessUnit(BusinessUnit partialBusinessUnit, long businessUnitId) {

        if(partialBusinessUnit.getCompany()!= null){
            GenericResponse<Company> companyResponse=companyService.getCompanyById(partialBusinessUnit.getCompany().getId());
            if(companyResponse.getError()!=null){
                return new GenericResponse<>(companyResponse.getError());
            }
            GenericResponse<BusinessUnitResponse> businessUnitResponse=businessUnitService.updateBusinessUnit(partialBusinessUnit,businessUnitId,companyResponse.getData());
            if(businessUnitResponse.getError()!=null){
                return new GenericResponse<>(businessUnitResponse.getError());
            }
            return businessUnitResponse;
        }
        GenericResponse<BusinessUnitResponse> businessUnitResponse= businessUnitService.updateBusinessUnit(partialBusinessUnit,businessUnitId,null);
        if(businessUnitResponse.getError()!=null){
            return new GenericResponse<>(businessUnitResponse.getError());
        }
        return businessUnitResponse;
    }


}

package com.example.service;

import com.example.dto.CompanyDto;
import com.example.entity.Company;

import java.util.List;

public interface VehicleCompanyService {
    void add(CompanyDto companyDto);

    List<Company> getVehicleList();
}

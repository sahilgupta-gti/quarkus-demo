package com.example.resource;

import com.example.dto.CompanyDto;
import com.example.entity.Company;
import com.example.entity.Vehicle;
import com.example.service.VehicleCompanyCompanyServiceImpl;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/vehicles")
public class VehicleCompanyResource {

    @Inject
    VehicleCompanyCompanyServiceImpl vehicleCompanyService;

    @GET
    @Path("/get")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Company> list() {
        return vehicleCompanyService.getVehicleList();
    }

    @POST
    @Path("/add")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public List<Company> add(CompanyDto companyDto) {
        vehicleCompanyService.add(companyDto);
        return list();
    }
}

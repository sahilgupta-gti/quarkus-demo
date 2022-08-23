package com.example.resource;

import com.example.entity.Employee;
import com.example.repo.EmployeeRepo;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path(value = "/employees")
@Slf4j
public class EmployeeResource {

    @Inject
    EmployeeRepo employeeRepo;


    @Produces(MediaType.APPLICATION_JSON)
    @GET
    @Path("/get")
    public List<Employee> getAllEmployees() {
        return employeeRepo.findAll().list();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/add")
    public List<Employee> addEmployee(Employee employee) {
        if(employee.getSource() == null) {
            employee.setSource(ObjectId.get().toString());
        }
        log.info("Employee: {}", employee);
        employeeRepo.persist(employee);
        return employeeRepo.findAll().list();
    }


}

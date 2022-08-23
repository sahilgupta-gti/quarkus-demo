package com.example.repo;

import com.example.entity.Employee;
import io.quarkus.mongodb.panache.PanacheMongoRepository;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class EmployeeRepo implements PanacheMongoRepository<Employee> {

}

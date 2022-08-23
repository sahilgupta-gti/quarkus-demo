package com.example.service;

import com.example.dto.CompanyDto;
import com.example.entity.Car;
import com.example.entity.Company;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import lombok.extern.slf4j.Slf4j;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@ApplicationScoped
@Slf4j
public class VehicleCompanyCompanyServiceImpl implements VehicleCompanyService {

    @Inject
    MongoClient mongoClient;

    //@ConfigProperty(name = "application.db.name")
    String dbName = "vehicle";

    //@ConfigProperty(name = "application.db.collection")
    String collectionName = "vehicles";

    private MongoCollection<Company> getCollection() {
        log.info("getCollection: " + collectionName);
        log.info("getDatabase: " + dbName);
        return mongoClient.getDatabase(dbName).withCodecRegistry(VehicleCompanyCodecProvider.getVehicleCompanyCodecRegistry())
                .getCollection(collectionName, Company.class);
    }

    @Override
    public void add(CompanyDto companyDto) {
        Company company = new Company();
        company.setName(companyDto.getName());
        if(companyDto.getType() != null && companyDto.getType().equals("car")) {
            log.info("companyDto: " + companyDto);
            log.info("companyType: " + companyDto.getVehicles().get(0).getClass());

//            private String name;
//            private String wheels;
//            private String color;
//            private double price;

            company.setVehicles(companyDto.getVehicles()
                    .stream().map( vehicle -> {
                        Map obj = (Map) vehicle;
                        Car car = new Car();
                        car.setColor((String) obj.get("color"));
                        car.setWheels((String) obj.get("wheels"));
                        car.setName((String) obj.get("name"));
                        //car.setPrice((Double) obj.get("price"));
                        return car;
                    }).collect(Collectors.toList()));
        }
        getCollection().insertOne(company);
    }

    @Override
    public List<Company> getVehicleList() {
        List<Company> companies = new ArrayList<>();
        try (MongoCursor<Company> cursor = getCollection().find().iterator()) {
            while (cursor.hasNext()) {
                companies.add(cursor.next());
            }
        }
        return companies;
    }
}

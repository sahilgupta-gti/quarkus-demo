package com.example.service;

import com.example.entity.Boat;
import com.example.entity.Car;
import com.example.entity.Company;
import com.example.entity.Vehicle;
import lombok.extern.slf4j.Slf4j;
import org.bson.codecs.configuration.CodecProvider;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.ClassModel;
import org.bson.codecs.pojo.ClassModelBuilder;
import org.bson.codecs.pojo.PojoCodecProvider;
import org.bson.codecs.pojo.PropertyModel;
import org.bson.codecs.pojo.PropertyModelBuilder;
import org.bson.codecs.pojo.PropertySerialization;

import static com.mongodb.MongoClientSettings.getDefaultCodecRegistry;
import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;
import static org.bson.codecs.pojo.Conventions.DEFAULT_CONVENTIONS;

@Slf4j
public class VehicleCompanyCodecProvider {
    private VehicleCompanyCodecProvider() {
    }

    public static CodecRegistry getVehicleCompanyCodecRegistry() {

        ClassModelBuilder<Company> classModelBuilder = ClassModel.builder(Company.class);
        classModelBuilder
                .getProperty("vehicles")
                .propertySerialization(new VehicleCompanyPropertySerialization());
        ClassModel<Company> companyClassModel = classModelBuilder.build();

        ClassModel<Vehicle> vehicleClassModel = ClassModel.builder(Vehicle.class).enableDiscriminator(true).build();
        ClassModel<Boat> boatClassModel = ClassModel.builder(Boat.class)
                .enableDiscriminator(true)
                .conventions(DEFAULT_CONVENTIONS)
                .build();
        ClassModel<Car> carClassModel = ClassModel.builder(Car.class)
                .enableDiscriminator(true)
                .conventions(DEFAULT_CONVENTIONS)
                .build();



        log.info("boatClassModel: {}", boatClassModel);
        log.info("boatClassModel: {}", boatClassModel.getIdPropertyModel());

        CodecProvider pojoCodecProvider = PojoCodecProvider.builder()
                .automatic(true)
                .register(companyClassModel, vehicleClassModel, boatClassModel, carClassModel)
                .build();
        return fromRegistries(getDefaultCodecRegistry(), fromProviders(pojoCodecProvider));

    }
}


class VehicleCompanyPropertySerialization implements PropertySerialization {
    @Override
    public boolean shouldSerialize(Object value) {
        return true;
    }
}
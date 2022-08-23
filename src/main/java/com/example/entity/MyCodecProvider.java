package com.example.entity;

import com.mongodb.MongoClientSettings;
import org.bson.codecs.configuration.CodecProvider;
import org.bson.codecs.configuration.CodecRegistries;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.ClassModel;
import org.bson.codecs.pojo.PojoCodecProvider;

import static org.bson.codecs.pojo.Conventions.DEFAULT_CONVENTIONS;

public class MyCodecProvider {

    private MyCodecProvider() { }

    public static CodecRegistry getCodecProvider(){
        ClassModel<Boat> boatClassModel = ClassModel.builder(Boat.class).build();
        ClassModel<Car> carClassModel = ClassModel.builder(Car.class).build();
        ClassModel<Vehicle> vehicleClassModel = ClassModel.builder(Vehicle.class).build();
        CodecProvider pojoCodecProvider = PojoCodecProvider
                .builder()
                .conventions(DEFAULT_CONVENTIONS)
                .register(Car.class, Boat.class, Vehicle.class)
                .build();
        return CodecRegistries.fromRegistries(MongoClientSettings.getDefaultCodecRegistry(),
                CodecRegistries.fromProviders(pojoCodecProvider));

    }
}

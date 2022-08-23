package com.example.entity;

import io.quarkus.mongodb.panache.common.MongoEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.bson.BsonType;
import org.bson.codecs.pojo.annotations.BsonId;
import org.bson.codecs.pojo.annotations.BsonProperty;
import org.bson.codecs.pojo.annotations.BsonRepresentation;

@Setter
@Getter
@ToString
@NoArgsConstructor
@MongoEntity(collection = "employees", database = "employee")
public class Employee {

    @BsonId
    @BsonProperty("_id")
    @BsonRepresentation(BsonType.OBJECT_ID)
    private String id;
    private String name;
    private int age;
    private double salary;

    @BsonProperty("_source")
    @BsonRepresentation(BsonType.OBJECT_ID)
    private String source;
}

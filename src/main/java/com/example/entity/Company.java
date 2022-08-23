package com.example.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.bson.BsonType;
import org.bson.codecs.pojo.annotations.BsonId;
import org.bson.codecs.pojo.annotations.BsonProperty;
import org.bson.codecs.pojo.annotations.BsonRepresentation;

import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class Company {
    @BsonId
    @BsonProperty(value = "_id")
    @BsonRepresentation(BsonType.OBJECT_ID)
    private String id;
    private String name;
    private String type;
    private List<Vehicle> vehicles;

}

package com.example.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.bson.BsonType;
import org.bson.codecs.pojo.annotations.BsonDiscriminator;
import org.bson.codecs.pojo.annotations.BsonId;
import org.bson.codecs.pojo.annotations.BsonIgnore;
import org.bson.codecs.pojo.annotations.BsonProperty;
import org.bson.codecs.pojo.annotations.BsonRepresentation;
import org.bson.types.ObjectId;

@Getter
@Setter
@ToString
@NoArgsConstructor
@BsonDiscriminator(value = "com.example.entity.Car", key = "_source")
public class Car implements Vehicle {
    @BsonId
    @BsonProperty(value = "_id")
    @BsonRepresentation(BsonType.OBJECT_ID)
    private ObjectId id;
    private ObjectId entityId;
    private String name;
    private String wheels;
    private String color;
    private double price;
}

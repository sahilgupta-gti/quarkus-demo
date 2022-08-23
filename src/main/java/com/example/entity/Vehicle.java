package com.example.entity;

import org.bson.codecs.pojo.annotations.BsonDiscriminator;

@BsonDiscriminator(value = "com.example.entity.Vehicle", key = "_source")
public interface Vehicle {
}

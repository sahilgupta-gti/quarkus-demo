package com.example.entity;

import org.bson.codecs.pojo.PropertySerialization;

public class MyObjectPropertySerialization implements PropertySerialization {
    @Override
    public boolean shouldSerialize(Object value) {
        return true;
    }
}

package com.example.entity;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class Student {
    private String id;
    private String name;
    private int age;
    private String className;
    private String type;
    private String source;
}


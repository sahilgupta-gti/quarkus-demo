package com.example.dto;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class StudentDto {

    private String enrollmentNumber;
    private String name;
    private int age;
    private String className;
    private String type;
    private String source;

}

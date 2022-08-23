package com.example.dto;

import com.example.entity.Vehicle;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class CompanyDto {

    private String id;
    private String name;
    private String type;
    private List<Object> vehicles;


}

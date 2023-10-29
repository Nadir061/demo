package com.example.demo.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class PersonDto {
    private Integer personId;
    private String firstName;
    private String lastName;
    private String post;
    private String salary;
}

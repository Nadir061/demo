package com.example.demo;

import lombok.*;
import org.springframework.stereotype.Component;

@NoArgsConstructor
@AllArgsConstructor
@Setter @Getter
@ToString
@Component
public class Person {
    private String firstName;
    private String lastName;
}

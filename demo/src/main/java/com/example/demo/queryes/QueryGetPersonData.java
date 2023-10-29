package com.example.demo.queryes;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("person")
@Setter
@Getter
public class QueryGetPersonData {
    private String queryPerson;
    private String queryEmployee;
    private String queryAddresses;
}

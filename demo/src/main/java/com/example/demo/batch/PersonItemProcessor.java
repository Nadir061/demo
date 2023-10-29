package com.example.demo.batch;

import com.example.demo.dto.EmployeesDto;
import com.example.demo.dto.PersonDto;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

@Component
public class PersonItemProcessor implements ItemProcessor<PersonDto, EmployeesDto> {

    @Override
    public EmployeesDto process(PersonDto personDto) throws Exception {

        return null;
    }
}

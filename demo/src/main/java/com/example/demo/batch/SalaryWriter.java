package com.example.demo.batch;

import com.example.demo.dto.EmployeesDto;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;

public class SalaryWriter implements ItemWriter<EmployeesDto> {
    @Override
    public void write(Chunk<? extends EmployeesDto> chunk) throws Exception {

    }
}

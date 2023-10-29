package com.example.demo.conf;

import com.example.demo.dto.EmployeesDto;
import com.example.demo.dto.PersonDto;
import com.example.demo.queryes.QueryGetPersonData;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobScope;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.batch.item.database.builder.JdbcCursorItemReaderBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

@Configuration
public class BatchConfiguration {

    @Autowired
    ApplicationContext context;
    @Autowired
    QueryGetPersonData queryGetPersonData;
    @Autowired
    JobRepository jobRepository;
    @Autowired
    DataSource dataSource;

//    @Bean
//    public DataSource dataSource() {
//        return DataSourceBuilder.create().build();
//    }

    @Bean
    public Job job(@Qualifier("uppingSalary") Step uppingSalaryStep, JobRepository jobRepository) {
        return new JobBuilder("uppingSalaryJob", jobRepository)
                .start(uppingSalaryStep)
                .build();
    }

    @JobScope
    @Bean
    @Qualifier("uppingSalary")
    public Step createUppingSalaryStep(ItemReader<PersonDto> reader,
                                       ItemProcessor<PersonDto, EmployeesDto> processor,
                                       ItemWriter<EmployeesDto> writer,
                                       JobRepository jobRepository,
                                       PlatformTransactionManager transactionManager) {
        return new StepBuilder("uppingSalary", jobRepository)
                .<PersonDto, EmployeesDto>chunk(1, transactionManager)
                .reader(reader)
                .processor(processor)
                .writer(writer)
                .build();
    }


    @Bean
    public ItemReader<PersonDto> createReader(@Qualifier("dataSource") DataSource dataSource,
                                              RowMapper<PersonDto> rowMapper) {
        return new JdbcCursorItemReaderBuilder<PersonDto>()
                .dataSource(dataSource)
                .sql(queryGetPersonData.getQueryPerson())
                .rowMapper(rowMapper)
                .build();
    }

    @Bean
    public ItemWriter<EmployeesDto> createWriter(@Qualifier("dataSource") DataSource dataSource) {
        return new JdbcBatchItemWriterBuilder<EmployeesDto>()
                .dataSource(dataSource)
                .sql(queryGetPersonData.getQueryEmployee())
                .beanMapped()
                .build();
    }

    @Bean
    public RowMapper<PersonDto> getRowMapperPerson() {
        return new BeanPropertyRowMapper<>(PersonDto.class);
    }

    @Bean
    public RowMapper<EmployeesDto> getRowMapperEmployee() {
        return new BeanPropertyRowMapper<>(EmployeesDto.class);
    }
}

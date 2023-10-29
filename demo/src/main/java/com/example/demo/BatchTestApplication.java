package com.example.demo;

import com.example.demo.queryes.QueryGetPersonData;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableBatchProcessing(dataSourceRef = "batchDataSource", transactionManagerRef = "transactionManagerRef")
@EnableConfigurationProperties(QueryGetPersonData.class)
public class BatchTestApplication {

    public static void main(String[] args) throws Exception {
        Launcher launcher = SpringApplication.run(BatchTestApplication.class, args).getBean(Launcher.class);
        launcher.launchJob();
    }
}

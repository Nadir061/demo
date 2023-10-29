package com.example.demo;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.launch.support.TaskExecutorJobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class Launcher {
    private final JobLauncher jobLauncher = new TaskExecutorJobLauncher();
    private final Job job;

    @Autowired
    public Launcher(Job job) {
        this.job = job;
    }


    public void launchJob() throws Exception {
        jobLauncher.run(job, new JobParametersBuilder().addDate("launchDate", new Date()).toJobParameters());
    }
}

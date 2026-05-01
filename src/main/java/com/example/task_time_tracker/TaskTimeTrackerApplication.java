package com.example.task_time_tracker;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.example.task_time_tracker.mapper")
public class TaskTimeTrackerApplication {

	public static void main(String[] args) {
		SpringApplication.run(TaskTimeTrackerApplication.class, args);
	}

}
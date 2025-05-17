package com.lido.bffschedulertasks;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class BffSchedulerTasksApplication {

	public static void main(String[] args) {
		SpringApplication.run(BffSchedulerTasksApplication.class, args);
	}

}

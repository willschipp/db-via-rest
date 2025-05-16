package com.github.willschipp;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class,args);
	}


	@Bean
	public TaskExecutor taskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(10); // Increased to handle 10 concurrent requests
        executor.setMaxPoolSize(10);
        executor.setQueueCapacity(0); // No queueing, direct execution
        executor.setThreadNamePrefix("client-executor-");
        executor.initialize();
        return executor;		
	}

	@Bean
	RestTemplate restTemplate() {
		return new RestTemplate();
	}
}

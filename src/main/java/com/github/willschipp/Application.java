package com.github.willschipp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.Environment;

@SpringBootApplication
public class Application implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(Application.class,args);
	}


	@Autowired
	Environment environment;

	@Override
	public void run(String... args) throws Exception {
		if (environment.matchesProfiles("client")) {
			//trigger performance test
		}
	}

}

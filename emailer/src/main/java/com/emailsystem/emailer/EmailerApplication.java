package com.emailsystem.emailer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.retry.annotation.EnableRetry;

@SpringBootApplication
@EnableRetry
public class EmailerApplication {
	public static void main(String[] args) {
		SpringApplication.run(EmailerApplication.class, args);
	}

}

//https://github.com/odiliemmanuel/Emailer.git

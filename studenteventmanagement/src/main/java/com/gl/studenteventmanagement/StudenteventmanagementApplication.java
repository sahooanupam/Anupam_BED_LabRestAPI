package com.gl.studenteventmanagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.gl.studenteventmanagement", "com.gl.studenteventmanagement.controller"})
public class StudenteventmanagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(StudenteventmanagementApplication.class, args);
	}

}

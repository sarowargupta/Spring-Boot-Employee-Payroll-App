package com.employee.payrollappdevelopment;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
@Slf4j
public class PayrollappdevelopmentApplication {

	public static void main(String[] args) {

		ApplicationContext context = SpringApplication.run(PayrollappdevelopmentApplication.class, args);

		//Section:-03 & UC -02 Lombok library for logging
		log.info("Employee Payroll App started");

		//Section:-03 & UC-03 Determine the logging
		log.info("Employee Payroll App Started in {} Environment",context.getEnvironment().getProperty("environment"));

	}
}

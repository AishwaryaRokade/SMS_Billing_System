package com.services.sms.smsService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
/**
 * Created by Aishwarya Rokade
 */

@SpringBootApplication
@ComponentScan(basePackages = {"com.services.sms.smsService"})
public class SmsServiceApplication  {

	public static void main(String[] args) {
		System.out.println("application started");
		SpringApplication.run(SmsServiceApplication.class, args);
	}

}

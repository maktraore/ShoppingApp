package com.myshop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.myshop.*"})
public class myshoppingAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(myshoppingAppApplication.class, args);
	}

}

package com.markit.trade;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * SpringBootApplication class in order to start the application, contains main 
 * method which will be invoked. 
 * @author Karan Verma
 *
 */

@SpringBootApplication
public class PriceApplication {

	public static void main(String[] args) throws Exception {
		SpringApplication.run(PriceApplication.class, args);
	}
}

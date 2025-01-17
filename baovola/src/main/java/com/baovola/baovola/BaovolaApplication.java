package com.baovola.baovola;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.baovola.baovola")
public class BaovolaApplication {

	public static void main(String[] args) {
		SpringApplication.run(BaovolaApplication.class, args);
	}

}

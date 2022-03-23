package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication
@EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class})
public class VaneBaronarinApplication {

	public static void main(String[] args) {
		SpringApplication.run(VaneBaronarinApplication.class, args);
		//System.out.print("버전 : "+ org.springframework.core.SpringVersion.getVersion());
	}

}

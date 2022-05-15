package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoApplication {

	@SpringBootApplication
	public class DemoApplication implements CommandLineRunner {

		@Autowired
		private Operator operator;

		public static void main(String[] args) {
			SpringApplication.run(com.example.demo.DemoApplication.class, args);
		}

		@Override
		public void run(String... args) {
			operator.connection();
		}

	}
}
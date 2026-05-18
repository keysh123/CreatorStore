package com.example.CreatorStore;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CreatorStoreApplication {


	public static void main(String[] args) {
		Dotenv dotenv = Dotenv.configure().directory("./CreatorStore").ignoreIfMissing().load();
		dotenv.entries().forEach((entry)-> System.setProperty(entry.getKey(),entry.getValue()));
		System.out.println("DATABASE_URL = " + System.getProperty("DATABASE_URL"));
		SpringApplication.run(CreatorStoreApplication.class, args);
	}

}

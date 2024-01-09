package com.example.demo;

import com.example.demo.model.Sector;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class System01Application {

	public static void main(String[] args) {
		SpringApplication.run(System01Application.class, args);

		System.out.println("Servidor rodando");
	}

}

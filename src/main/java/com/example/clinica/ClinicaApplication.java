package com.example.clinica;

import com.example.clinica.bd.BD;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ClinicaApplication {

	public static void main(String[] args) {
		BD.crearBD();
		SpringApplication.run(ClinicaApplication.class, args);
	}

}

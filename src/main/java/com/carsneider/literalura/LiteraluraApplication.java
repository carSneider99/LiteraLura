package com.carsneider.literalura;

import com.carsneider.literalura.Principal.PrincipalBooks;
import com.carsneider.literalura.service.AutorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LiteraluraApplication implements CommandLineRunner {

	@Autowired
	private AutorService autorService;


	public static void main(String[] args) {
		SpringApplication.run(LiteraluraApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		PrincipalBooks principalBooks = new PrincipalBooks(autorService);
		principalBooks.mostrarMenu();
	}
}
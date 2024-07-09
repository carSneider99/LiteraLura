package com.carsneider.literalura;

import com.carsneider.literalura.Principal.PrincipalBooks;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LiteraluraApplication {

	public static void main(String[] args) {
		SpringApplication.run(LiteraluraApplication.class, args);
		PrincipalBooks.mostrarMenu();
	}
}
package com.carsneider.literalura.repository;

import com.carsneider.literalura.entity.Libro;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface LibroRepository extends JpaRepository<Libro, Integer> {
    Optional<Libro> findByTituloIgnoreCaseAndAutorId(String titulo, Integer autorId);

    List<Libro> findByIdioma(String idioma);
}

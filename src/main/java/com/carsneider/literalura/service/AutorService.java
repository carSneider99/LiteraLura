package com.carsneider.literalura.service;

import com.carsneider.literalura.entity.Autor;
import com.carsneider.literalura.entity.Libro;
import com.carsneider.literalura.repository.AutorRepository;
import com.carsneider.literalura.repository.LibroRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Log4j2
@Service
public class AutorService {
    @Autowired
    private AutorRepository autorRepository;

    @Autowired
    private LibroRepository libroRepository;

    public void saveAutorYLibro(Autor autor, Libro libro){
        Optional<Autor> autorEncontrado = autorRepository.findByNombreIgnoreCase(autor.getNombre());

        if (autorEncontrado.isPresent()){
            log.info("Autor ya registrado en base de datos: " + autor.getNombre());
            Optional<Libro> libroEncontrado = libroRepository.findByTituloIgnoreCaseAndAutorId(libro.getTitulo(),autorEncontrado.get().getId());
            libro.setAutor(autorEncontrado.get());
            if (libroEncontrado.isEmpty()){
                log.info("Se almacena nuevo libro: " + libro.getTitulo());
                libroRepository.save(libro);
            } else {
                System.out.println("No se puede registrar el mismo libro más de una vez: " + libro.getTitulo());
            }
        } else {
            log.info("Se almacena nuevo autor: " + autor.getNombre());
            log.info("Se almacena nuevo libro: " + libro.getTitulo());
            autor.addLibro(libro);
            libro.setAutor(autor);
            autorRepository.save(autor);
        }
    }

    public List<Libro> listarTodosLosLibros(){
        return libroRepository.findAll();
    }

    public List<Autor> listarTodosLosAutores(){
        return autorRepository.findAll();
    }

    public List<Autor> listarAutoresVivos(int year){
        return autorRepository.findByFechaNacimientoLessThanEqualAndFechaFallecimientoGreaterThanEqual(year,year);
    }

    public List<Libro> listarPorIdioma(String idioma){
        return libroRepository.findByIdioma(idioma);
    }
}
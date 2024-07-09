package com.carsneider.literalura.entity;

import com.carsneider.literalura.model.JSONAutor;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name="autores")
public class Autor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(unique = true, nullable = false)
    private String nombre;
    int fechaNacimiento;
    int fechaFallecimiento;

    @OneToMany(mappedBy = "autor", cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private List<Libro> libros = new ArrayList<>();

    public Autor (JSONAutor jsonAutor){
        this.nombre = jsonAutor.name();
        this.fechaNacimiento = jsonAutor.birthYear();
        this.fechaFallecimiento = jsonAutor.deathYear();
    }

    public void addLibro (Libro libro){
        libros.add(libro);
    }

    @Override
    public String toString() {
        StringBuilder retorno = new StringBuilder("Autor: " + nombre +
                "\nFecha de nacimiento: " + fechaNacimiento +
                "\nFecha de fallecimiento: " + fechaFallecimiento +
                "\nLibros: [");

        for (Libro libro: libros){
            retorno.append(libro.getTitulo()).append(",");
        }
        return retorno.substring(0,retorno.length()-1) + "]\n";
    }
}
package com.carsneider.literalura.entity;

import com.carsneider.literalura.model.JSONLibro;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name="libros")
public class Libro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(unique = true, nullable = false)
    private String titulo;
    private String idioma;
    private int numDescargas;

    @ManyToOne
    private Autor autor;

    public Libro (JSONLibro jsonLibro){
        this.titulo = jsonLibro.title();
        this.idioma = jsonLibro.languages().get(0);
        this.numDescargas = jsonLibro.descargas();
    }

    @Override
    public String toString() {
        return "----- Libro -----" +
                "\nTítulo: " + titulo +
                "\nAutor:" + autor.getNombre() +
                "\nIdioma: " + idioma +
                "\nNúmero de descargas: " + numDescargas +
                "\n-----------------\n";
    }
}
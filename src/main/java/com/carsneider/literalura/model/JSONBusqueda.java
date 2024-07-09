package com.carsneider.literalura.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record JSONBusqueda(
        @JsonAlias("count") Integer contBusqueda,
        @JsonAlias("results")List<JSONLibro> libros) {
}
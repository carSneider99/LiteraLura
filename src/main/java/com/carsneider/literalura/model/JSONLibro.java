package com.carsneider.literalura.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record JSONLibro(
        String title,
        List<JSONAutor> authors,
        List<String> languages,
        @JsonAlias("download_count") int descargas
) {
}
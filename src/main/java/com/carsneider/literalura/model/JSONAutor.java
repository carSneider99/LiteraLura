package com.carsneider.literalura.model;

import com.fasterxml.jackson.annotation.JsonAlias;

public record JSONAutor(
        String name,
        @JsonAlias("birth_year") int birthYear,
        @JsonAlias("death_year") int death_year
) {
}
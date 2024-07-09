package com.carsneider.literalura.model;

public enum Idioma {
    es("Español"),
    en("Inglés"),
    fr("Francés"),
    pt("Portugués");

    private String descrIdioma;

    Idioma(String descrIdioma) {
        this.descrIdioma = descrIdioma;
    }


    public static Idioma fromString (String text) {
        for (Idioma idiomaItem : Idioma.values()) {
            if (idiomaItem.descrIdioma.equalsIgnoreCase(text))
                return idiomaItem;
        }

        throw new IllegalArgumentException("Idioma no encontrado: " + text);
    }
}
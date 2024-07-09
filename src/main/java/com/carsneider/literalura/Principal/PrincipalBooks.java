package com.carsneider.literalura.Principal;

import com.carsneider.literalura.model.JSONBusqueda;
import com.carsneider.literalura.service.Utils;

import java.util.Scanner;


public class PrincipalBooks {
    private static final String URL_BASE =  "https://gutendex.com/books/?search=";
    public static void mostrarMenu(){

        Scanner teclado = new Scanner(System.in);
        System.out.print("Escriba el nombre del libro que desee buscar: ");
        String nomLibro = teclado.next().replace(" ","+");

        // Busca los datos generales del libro
        String json = Utils.consumirAPI(URL_BASE + nomLibro);
        JSONBusqueda jsonBusqueda = Utils.obtenerDatos(json, JSONBusqueda.class);
        System.out.println(jsonBusqueda);
    }
}
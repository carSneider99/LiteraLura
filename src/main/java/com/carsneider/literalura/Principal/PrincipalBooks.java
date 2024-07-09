package com.carsneider.literalura.Principal;

import com.carsneider.literalura.entity.Autor;
import com.carsneider.literalura.entity.Libro;
import com.carsneider.literalura.model.JSONBusqueda;
import com.carsneider.literalura.repository.AutorRepository;
import com.carsneider.literalura.service.AutorService;
import com.carsneider.literalura.service.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Scanner;

@Service
public class PrincipalBooks {
    private final String URL_BASE =  "https://gutendex.com/books/?search=";
    private final Scanner teclado = new Scanner(System.in);

    private AutorService autorService;

    public PrincipalBooks(AutorService autorService) {
        this.autorService = autorService;
    }

    public void mostrarMenu(){

        int opcion = -1;
        while (opcion != 0){
            System.out.println("""
                    1 - buscar libro por título
                    2 - listar libros registrados
                    3 - listar autores registrados
                    4 - listar autores vivos en un determinado año
                    5 - listar libros por idioma
                    0 - salir
                    """);
            System.out.print("Elija la opción a través de su número: ");
            opcion = teclado.nextInt();
            teclado.nextLine();

            switch (opcion){
                case 1: buscarLibroAPI(); break;
                case 2: listarLibros(); break;
                case 3: listarAutores(); break;
                //case 4: listarAutoresVivos(); break;
                //case 5: listarLibrosPorIdiomas(); break;
                case 0: System.out.println("Cerrando aplicación"); break;

                default:
                    System.out.println("Opción inválida");
            }
        }
    }

    private void buscarLibroAPI() {

        System.out.print("Escriba el nombre del libro que desee buscar: ");
        String nomLibro = teclado.nextLine();

        //Busca los datos generales del libro
        String json = Utils.consumirAPI(URL_BASE + nomLibro.replace(" ", "+"));
        JSONBusqueda jsonBusqueda = Utils.obtenerDatos(json, JSONBusqueda.class);

        if (jsonBusqueda.libros().isEmpty()) {
            System.out.println("Libro no encontrado: " + nomLibro);
            return;
        }

        Libro libro = new Libro(jsonBusqueda.libros().get(0));
        Autor autor = new Autor(jsonBusqueda.libros().get(0).authors().get(0));

        //Se persiste autor y libro según lógica de negocio
        autorService.saveAutorYLibro(autor, libro);
    }

    private void listarLibros() {
        autorService.listarTodosLosLibros().forEach(System.out::println);
    }

    private void listarAutores() {
        autorService.listarTodosLosAutores().forEach(System.out::println);
    }
}
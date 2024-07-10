package com.carsneider.literalura.Principal;

import com.carsneider.literalura.entity.Autor;
import com.carsneider.literalura.entity.Libro;
import com.carsneider.literalura.model.JSONBusqueda;
import com.carsneider.literalura.service.AutorService;
import com.carsneider.literalura.service.Utils;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Scanner;

@Service
public class PrincipalBooks {
    private final Scanner teclado = new Scanner(System.in);

    private final AutorService autorService;

    public PrincipalBooks(AutorService autorService) {
        this.autorService = autorService;
    }

    public void mostrarMenu(){

        int opcion = -1;
        while (opcion != 0){
            System.out.println();
            System.out.println("""
                    1 - buscar libro por título
                    2 - listar libros registrados
                    3 - listar autores registrados
                    4 - listar autores vivos en un determinado año
                    5 - listar libros por idioma
                    0 - salir""");
            System.out.print("Elija la opción a través de su número: ");
            opcion = teclado.nextInt();
            teclado.nextLine();

            switch (opcion){
                case 1: buscarLibroAPI(); break;
                case 2: listarLibros(); break;
                case 3: listarAutores(); break;
                case 4: listarAutoresVivos(); break;
                case 5: listarLibrosPorIdiomas(); break;
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
        String URL_BASE = "https://gutendex.com/books/?search=";
        String json = Utils.consumirAPI(URL_BASE + nomLibro.replace(" ", "+"));
        JSONBusqueda jsonBusqueda = Utils.obtenerDatos(json, JSONBusqueda.class);

        if (jsonBusqueda.contBusqueda() == 0) {
            System.out.println("Libro no encontrado: " + nomLibro);
            return;
        }

        Libro libro = new Libro(jsonBusqueda.libros().get(0));
        Autor autor = new Autor(jsonBusqueda.libros().get(0).authors().get(0));

        //Se persiste autor y libro según lógica de negocio
        autorService.saveAutorYLibro(autor, libro);
        System.out.println(libro);
    }

    private void listarLibros() {
        autorService.listarTodosLosLibros().forEach(System.out::println);
    }

    private void listarAutores() {
        autorService.listarTodosLosAutores().forEach(System.out::println);
    }

    private void listarAutoresVivos() {
        System.out.print("Ingrese el año vivo de autor(es) que desea buscar: ");
        int year = teclado.nextInt();
        teclado.nextLine();

        autorService.listarAutoresVivos(year).forEach(System.out::println);
    }

    private void listarLibrosPorIdiomas (){
        Map<String, String> idiomas = Map.of("es","español","en","inglés","fr","francés","pt","portugués");
        idiomas.forEach((key,valor) -> System.out.println(key + " - " + valor));
        System.out.print("ingrese el idioma para buscar los libros: ");
        String idioma = teclado.nextLine();

        if (!idiomas.containsKey(idioma)){
            System.out.println("Idioma inválido, corrija");
            return;
        }

        autorService.listarPorIdioma(idioma).forEach(System.out::println);
    }
}
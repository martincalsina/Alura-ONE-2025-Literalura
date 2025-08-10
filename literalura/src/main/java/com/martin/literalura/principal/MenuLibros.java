package com.martin.literalura.principal;

import com.martin.literalura.model.Libro;
import com.martin.literalura.model.PaginaDeLibros;
import com.martin.literalura.service.ConsultasAPILibros;

import java.util.Scanner;

public class MenuLibros {

    private ConsultasAPILibros consultaAPI = new ConsultasAPILibros();
    private final String menuPrincipalOpciones = """
                    1 - Buscar libro por título.
                    2 - Listar libros registrados.
                    3 - Listar autores registrados.
                    4 - Listar autores registrados vivos en un determinado año.
                    5 - Listar libros registrados por idioma.
                    6 - Salir.
                    """;
    private Scanner lectura = new Scanner(System.in);

    public void iniciar() {
        String mensajeBienvenida = """
				Bienvenido/a Literarua, aquí puede
				""";
        System.out.println(mensajeBienvenida);
        while (true) {

            System.out.println("¿Qué desea hacer?");;
            System.out.println(menuPrincipalOpciones);
            Integer opcionElegida = null;
            try {
                opcionElegida = Integer.valueOf(lectura.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Porfavor, ingrese un valor entero");
                continue;
            }

            if (1 <= opcionElegida && opcionElegida <= 5) {
                this.atenderOpcion(opcionElegida);
            } else if(opcionElegida == 6) {
                break;
            } else {
                System.out.println("Porfavor, ingrese un valor de entre las opciones dadas");
            }
        }
    }

    private void atenderOpcion(Integer opcionElegida) {
        switch(opcionElegida) {
            case 1:
                this.buscarLibroPorTitulo();
                break;
            case 2:
                break;
            case 3:
                break;
            case 4:
                break;
            case 5:
                break;

        }
    }

    private void buscarLibroPorTitulo() {
        System.out.println("Ingrese el nombre del libro que desea buscar:");
        String titulo = lectura.nextLine();
        System.out.println("Buscando libro con el título '" + titulo + "'");
        PaginaDeLibros paginaDeLibros = consultaAPI.getBookPageByName(titulo);
        if (paginaDeLibros.libros().isEmpty()) {
            System.out.println("No se encontraron libros con ese título");
        } else {
            System.out.println("Encontramos el siguiente libro:");
            this.imprimirLibro(paginaDeLibros.libros().get(0));
        }
    }

    private void imprimirLibro(Libro libro) {
        System.out.println("Título: " + libro.titulo());
        System.out.println("Autor: " + libro.autores().get(0).nombre());
        System.out.println("Resumen: " + libro.resumenes().get(0));
        System.out.println("Idioma: " + libro.idiomas().get(0));
    }

}

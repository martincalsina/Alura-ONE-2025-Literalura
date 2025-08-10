package com.martin.literalura.principal;

import com.martin.literalura.model.Autor;
import com.martin.literalura.model.DatosLibro;
import com.martin.literalura.model.Libro;
import com.martin.literalura.model.PaginaDeLibros;
import com.martin.literalura.repository.AutorRepository;
import com.martin.literalura.repository.LibroRepository;
import com.martin.literalura.service.ConsultasAPILibros;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class MenuLibros {

    @Autowired
    private LibroRepository libroRepository;
    @Autowired
    private AutorRepository autorRepository;
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
                this.listarLibrosRegistrados();
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
        if (paginaDeLibros.datosLibros().isEmpty()) {
            System.out.println("No se encontraron libros con ese título");
        } else {
            DatosLibro datosLibro = paginaDeLibros.datosLibros().get(0);
            Libro libro = new Libro(datosLibro);
            System.out.println("Encontramos el siguiente libro:");
            System.out.println(libro);
            this.registrar(libro);
        }
    }

    private void registrar(Libro libro) {
        if (!libroRepository.existsById(libro.getId())) {
            if (!autorRepository.existsById(libro.getAutor().getNombre())) {
                Autor autor = libro.getAutor();
                System.out.println("Registrando autor...");
                autorRepository.save(autor);
            }
            System.out.println("Registrando libro...");
            libroRepository.save(libro);
        }
    }

    private void listarLibrosRegistrados() {
        List<Libro> librosRegistrados = this.libroRepository.findAll();
        System.out.println("Listando libros registrados");
        System.out.println("--------------------------------");
        for(Libro libro: librosRegistrados) {
            System.out.println(libro);
        }
        System.out.println("--------------------------------");
    }

}

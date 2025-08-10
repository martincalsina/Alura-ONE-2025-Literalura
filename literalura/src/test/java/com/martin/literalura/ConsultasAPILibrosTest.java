package com.martin.literalura;

import com.martin.literalura.model.Libro;
import com.martin.literalura.model.PaginaDeLibros;
import com.martin.literalura.service.ConsultasAPILibros;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class ConsultasAPILibrosTest {

    private final ConsultasAPILibros consultasAPI = new ConsultasAPILibros();

    @Test
    void test01AllBooksRequestReturnsAPageOfBooks() {

        PaginaDeLibros paginaDeLibros = consultasAPI.getAllBooks();

        assertNotNull(paginaDeLibros);
        assertTrue(paginaDeLibros.cantidad() > 0, "La página de libros default no debe ser vacía");
        assertFalse(paginaDeLibros.siguientePagina().isBlank(), "La página de libros default debe tener siguiente página");
        assertNull(paginaDeLibros.anteriorPagina(), "La página de libros default no tiene anterior página");
        assertFalse(paginaDeLibros.libros().isEmpty(), "La lista de libros no debería estar vacía");

    }

    @Test
    void test02AllBooksRequestListOfBooksReturnsBooks() {

        PaginaDeLibros paginaDeLIbros = consultasAPI.getAllBooks();
        List<Libro> libros = paginaDeLIbros.libros();
        Libro libro = libros.get(0);

        assertNotNull(libro.id(), "El id de un libro no puede ser nulo");
        assertFalse(libro.titulo().isBlank(), "Un libro debe tener título");
        assertFalse(libro.autores().isEmpty(), "Un libro debe tener al menos un autor");
        assertFalse(libro.resumenes().isEmpty(), "Un libro debe tener al menos un resumen");

    }

}

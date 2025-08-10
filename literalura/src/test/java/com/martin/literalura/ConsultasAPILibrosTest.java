package com.martin.literalura;

import com.martin.literalura.model.PaginaDeLibros;
import com.martin.literalura.service.ConsultasAPILibros;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class ConsultasAPILibrosTest {

    @Test
    void test01AllBooksRequestReturnsAPageOfBooks() {

        ConsultasAPILibros consultasAPI = new ConsultasAPILibros();

        PaginaDeLibros paginaDeLibros = consultasAPI.getAllBooks();

        assertNotNull(paginaDeLibros);
        assertTrue(paginaDeLibros.cantidad() > 0, "La página de libros default no debe ser vacía");
        assertFalse(paginaDeLibros.siguientePagina().isBlank(), "La página de libros default debe tener siguiente página");
        assertNull(paginaDeLibros.anteriorPagina(), "La página de libros default no tiene anterior página");
        assertFalse(paginaDeLibros.libros().isEmpty(), "La lista de libros no debería estar vacía");


    }

}

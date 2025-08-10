package com.martin.literalura;

import com.martin.literalura.model.DatosLibro;
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

        PaginaDeLibros paginaDeLibros = consultasAPI.getAllBooksPage();

        assertNotNull(paginaDeLibros);
        assertTrue(paginaDeLibros.cantidad() > 0, "La página de libros default no debe ser vacía");
        assertFalse(paginaDeLibros.siguientePagina().isBlank(), "La página de libros default debe tener siguiente página");
        assertNull(paginaDeLibros.anteriorPagina(), "La página de libros default no tiene anterior página");
        assertFalse(paginaDeLibros.datosLibros().isEmpty(), "La lista de libros no debería estar vacía");

    }

    @Test
    void test02AllBooksRequestListOfBooksReturnsBooks() {

        PaginaDeLibros paginaDeLIbros = consultasAPI.getAllBooksPage();
        List<DatosLibro> datosLibros = paginaDeLIbros.datosLibros();
        DatosLibro datosLibro = datosLibros.get(0);

        assertNotNull(datosLibro.id(), "El id de un libro no puede ser nulo");
        assertFalse(datosLibro.titulo().isBlank(), "Un libro debe tener título");
        assertFalse(datosLibro.autores().isEmpty(), "Un libro debe tener al menos un autor");
        assertFalse(datosLibro.resumenes().isEmpty(), "Un libro debe tener al menos un resumen");

    }

    @Test
    void test03BookByTitleRequestReturnsNoResultsWhenBookIsNotPresent() {

        String notPresentBookTitle = "mkasdmak31";
        PaginaDeLibros paginaDeLibros = consultasAPI.getBookPageByName(notPresentBookTitle);

        assertNotNull(paginaDeLibros);
        assertEquals(0, (int) paginaDeLibros.cantidad(), "Deberían haberse hallado 0 libros");
        assertNull(paginaDeLibros.siguientePagina(), "No debería haber siguiente página");
        assertNull(paginaDeLibros.anteriorPagina(), "No debería haber anterior página");
        assertTrue(paginaDeLibros.datosLibros().isEmpty(), "No debería haberse recibido ningún libro");

    }

    //como tal, la API usa ?search=smth para hallar coincidencias de palabras entre titulo y autor, no hace diferencia. Pero a mí sólo me importa título
    @Test
    void test04BookByTitleRequestReturnsBooksWhenIsPresent() {

        String presentBookTitle = "Don Quijote";
        PaginaDeLibros paginaDeLibros = consultasAPI.getBookPageByName(presentBookTitle);

        assertNotNull(paginaDeLibros);
        assertTrue(paginaDeLibros.cantidad() > 0, "Deberían haberse hallado al menos un libro");
        assertNull(paginaDeLibros.anteriorPagina(), "No debería haber anterior página");
        assertFalse(paginaDeLibros.datosLibros().isEmpty(), "Debería haberse recibido al menos un libro");

    }

}

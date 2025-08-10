package com.martin.literalura.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record PaginaDeLibros(
        @JsonAlias("count") Integer cantidad,
        @JsonAlias("next") String siguientePagina,
        @JsonAlias("previous") String anteriorPagina,
        @JsonAlias("results") List<Libro> libros
        ) {
}

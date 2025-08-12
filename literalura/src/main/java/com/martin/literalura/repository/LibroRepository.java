package com.martin.literalura.repository;

import com.martin.literalura.model.Libro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LibroRepository extends JpaRepository<Libro, Long> {

    @Query(value = "SELECT DISTINCT l.idioma FROM libro AS l;", nativeQuery = true)
    List<String> obtenerIdiomas();

    List<Libro> findByIdiomaEquals(String idioma);
}

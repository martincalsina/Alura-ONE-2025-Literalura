package com.martin.literalura.repository;

import com.martin.literalura.model.Autor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AutorRepository extends JpaRepository<Autor, String> {

    @Query("SELECT a FROM Autor a WHERE a.anioNacimiento <= :anio AND a.anioMuerte >= :anio")
    public List<Autor> obtenerAutoresVivosEn(@Param("anio") Integer anio);

}

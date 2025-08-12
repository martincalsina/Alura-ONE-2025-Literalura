package com.martin.literalura.model;


import jakarta.persistence.*;

import java.util.List;

@Entity
public class Autor {

    @Id
    @Column(unique = true)
    private String nombre;
    private Integer anioNacimiento;
    private Integer anioMuerte;
    @OneToMany(mappedBy = "autor", cascade=CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Libro> libros;

    public Autor() {

    }

    public Autor(DatosAutor datosAutor) {
        this.nombre = datosAutor.nombre();
        this.anioNacimiento = datosAutor.anioNacimiento();
        this.anioMuerte = datosAutor.anioMuerte();
    }

    @Override
    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("Nombre: " + this.nombre +"\n");
        stringBuffer.append("Año de nacimiento: " + this.anioNacimiento+"\n");
        stringBuffer.append("Año de muerte: " + this.anioMuerte+"\n");
        stringBuffer.append("Libros registrados: "+"\n");
        for (Libro libro: libros) {
            stringBuffer.append(" - "+ libro.getTitulo() + "\n");
        }
        return stringBuffer.toString();
    }

    public String getNombre() {
        return this.nombre;
    }

    public List<Libro> getLibros() {
        return this.libros;
    }

}

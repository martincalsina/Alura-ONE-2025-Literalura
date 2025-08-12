package com.martin.literalura.model;

import jakarta.persistence.*;
import org.hibernate.annotations.Type;

;

@Entity
public class Libro {

    @Id
    private Long id;
    private String titulo;
    @Column(columnDefinition="TEXT")
    private String resumen;
    private String idioma;
    @ManyToOne
    @JoinColumn(name = "nombre")
    private Autor autor;

    public Libro() {

    }

    public Libro(DatosLibro datosLibro) {
        this.id = datosLibro.id();
        this.titulo = datosLibro.titulo();
        this.resumen = datosLibro.resumenes().get(0);
        this.idioma = datosLibro.idiomas().get(0);
        this.autor = new Autor(datosLibro.autores().get(0));
    }

    @Override
    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();

        stringBuffer.append("Título: " + this.titulo +"\n");
        stringBuffer.append("Autor: " + this.autor.getNombre() + "\n");
        stringBuffer.append("Resumen: " + this.resumen + "\n");
        stringBuffer.append("Idioma: " + this.idioma);

        return stringBuffer.toString();
    }

    public Long getId() {
        return this.id;
    }

    public String getTitulo() {
        return this.titulo;
    }

    public Autor getAutor() {
        return this.autor;
    }

}

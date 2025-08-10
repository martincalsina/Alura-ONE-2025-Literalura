package com.martin.literalura.model;

public class Libro {

    private Integer id;
    private String titulo;
    private String resumen;
    private String idioma;
    private Autor autor;

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
        stringBuffer.append("Autor: " + this.autor + "\n");
        stringBuffer.append("Resumen: " + this.resumen + "\n");
        stringBuffer.append("Idioma: " + this.idioma);

        return stringBuffer.toString();
    }

}

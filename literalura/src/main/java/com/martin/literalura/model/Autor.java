package com.martin.literalura.model;

public class Autor {

    private String nombre;
    private Integer anioNacimiento;
    private Integer anioMuerte;

    public Autor(DatosAutor datosAutor) {
        this.nombre = datosAutor.nombre();
        this.anioNacimiento = datosAutor.anioNacimiento();
        this.anioMuerte = datosAutor.anioMuerte();
    }

    @Override
    public String toString() {
        return this.nombre;
    }

}

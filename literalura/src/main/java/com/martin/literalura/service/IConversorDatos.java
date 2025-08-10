package com.martin.literalura.service;

public interface IConversorDatos {
    <T> T convertirDatos(String rawJson, Class<T> clase);
}

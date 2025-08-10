package com.martin.literalura.service;

import com.martin.literalura.model.PaginaDeLibros;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ConsultasAPILibros {

    private final String BASE_URL = "https://gutendex.com";
    private final ConversorDatos conversor = new ConversorDatos();

    public String obtenerDatos(String url) {
        HttpClient client = HttpClient.newBuilder()
                .followRedirects(HttpClient.Redirect.ALWAYS)
                .build();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .build();
        HttpResponse<String> response = null;
        try {
            response = client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return response.body();
    }

    public PaginaDeLibros getAllBooks() {
        String rawJson = this.obtenerDatos(BASE_URL + "/books");
        PaginaDeLibros paginaDeLibros = conversor.convertirDatos(rawJson, PaginaDeLibros.class);
        return paginaDeLibros;
    }

}

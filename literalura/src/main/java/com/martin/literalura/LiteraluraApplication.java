package com.martin.literalura;

import com.martin.literalura.principal.MenuLibros;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LiteraluraApplication implements CommandLineRunner {

	private final MenuLibros menu;

	@Autowired
	public LiteraluraApplication(MenuLibros menu) {
		this.menu = menu;
	}

	public static void main(String[] args) {
		SpringApplication.run(LiteraluraApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		menu.iniciar(); // Ahora sí: todas las dependencias están inyectadas
	}
}

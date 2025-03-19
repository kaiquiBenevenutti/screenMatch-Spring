package com.kaiqui.screenMatch;

import models.DadosSerie;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import services.ConsumoApi;
import services.ConversorDados;

@SpringBootApplication
public class ScreenMatchApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(ScreenMatchApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		ConsumoApi api = new ConsumoApi();

		var json = api.obterDados("https://www.omdbapi.com/?t=The+Boys&apikey=76536016");
		System.out.println(json);

		ConversorDados conversor = new ConversorDados();

		DadosSerie dados = conversor.obterDados(json, DadosSerie.class);

		System.out.println(dados);
	}
}

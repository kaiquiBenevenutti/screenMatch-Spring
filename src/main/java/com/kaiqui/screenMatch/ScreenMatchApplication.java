package com.kaiqui.screenMatch;

import models.DadosEpisodio;
import models.DadosSerie;
import models.DadosTemporada;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import services.ConsumoApi;
import services.ConversorDados;

import java.util.ArrayList;
import java.util.List;

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

		json = api.obterDados("https://www.omdbapi.com/?t=The+Boys&episode=6&season=2&apikey=76536016");

		DadosEpisodio ep = conversor.obterDados(json, DadosEpisodio.class);

		System.out.println(ep);

		List<DadosTemporada> temporadas = new ArrayList<>();

		for(var i = 0; i <= dados.totalTemporadas(); i++){
			json = api.obterDados("https://www.omdbapi.com/?t=The+Boys&season=" + i + "&apikey=76536016");
			DadosTemporada temporada = conversor.obterDados(json, DadosTemporada.class);
			temporadas.add(temporada);
		}

		temporadas.forEach(System.out::println);
	}
}

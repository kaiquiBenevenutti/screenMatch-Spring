package Principal;

import models.DadosEpisodio;
import models.DadosSerie;
import models.DadosTemporada;
import models.Episodio;
import services.ConsumoApi;
import services.ConversorDados;

import java.net.URI;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Principal{
    private final String ENDERECO = "https://www.omdbapi.com/?t=";
    private final String API_KEY = "&apikey=76536016";
    private ConsumoApi api = new ConsumoApi();
    private ConversorDados conversor = new ConversorDados();
    private List<DadosTemporada> temporadas = new ArrayList<>();

    Scanner scanner = new Scanner(System.in);

    public void exibeMenu(){
        System.out.println("Digite o nome da serie: ");
        var nome = scanner.nextLine();
        var json = api.obterDados(ENDERECO + nome.replace(" ", "+") + API_KEY);
        DadosSerie dados = conversor.obterDados(json, DadosSerie.class);
        System.out.println(dados);

        for(var i = 1; i <= dados.totalTemporadas(); i++){
            json = api.obterDados(ENDERECO + nome.replace(" ", "+") + "&Season=" + i + API_KEY);
            DadosTemporada temporada = conversor.obterDados(json, DadosTemporada.class);
            temporadas.add(temporada);
        }

        //temporadas.forEach(System.out::println);
        temporadas.forEach(t -> t.episodios().forEach(e -> System.out.println(e.titulo())));

        List<DadosEpisodio> episodios = temporadas.stream()
                .flatMap(t -> t.episodios().stream())
                .collect(Collectors.toList());

        System.out.println("\nTop 5 episodios:");

        episodios.stream()
                .filter(e -> !e.nota().equalsIgnoreCase("N/A"))
                .sorted(Comparator.comparing(DadosEpisodio::nota).reversed())
                .limit(5)
                .forEach(System.out::println);

        List<Episodio> eps = temporadas.stream()
                .flatMap(t -> t.episodios().stream()
                        .map(e -> new Episodio(t.temporada(), e)))
                        .collect(Collectors.toList());

        eps.forEach(System.out::println);
    }
}

package models;

import com.fasterxml.jackson.annotation.JsonAlias;

public record DadosSerie(@JsonAlias("Title") String title,@JsonAlias("totalSeasons") Integer totalTemporadas,@JsonAlias("imdbRating") String avaliacao) {

}

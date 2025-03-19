package models;

import com.fasterxml.jackson.annotation.JsonAlias;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Locale;

public class Episodio {
    private Integer Temporada;
    private String titulo;
    private Integer numero;
    private Double nota;
    private LocalDate dataLancamento;

    public Episodio(Integer temporada, DadosEpisodio d) {
        Temporada = temporada;
        this.titulo = d.titulo();
        this.numero = d.numero();
        try{
            this.nota = Double.valueOf(d.nota());
        } catch (NumberFormatException e){
            this.nota = 0.0;
        }
        try{
            this.dataLancamento = LocalDate.parse(d.dataLancamento());
        } catch (DateTimeParseException e){
            this.dataLancamento = null;
        }
    }

    public Integer getTemporada() {
        return Temporada;
    }

    public void setTemporada(Integer temporada) {
        Temporada = temporada;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public Double getNota() {
        return nota;
    }

    public void setNota(Double nota) {
        this.nota = nota;
    }

    public LocalDate getDataLancamento() {
        return dataLancamento;
    }

    public void setDataLancamento(LocalDate dataLancamento) {
        this.dataLancamento = dataLancamento;
    }

    @Override
    public String toString() {
        return
                "Temporada=" + Temporada +
                ", titulo='" + titulo + '\'' +
                ", numero=" + numero +
                ", nota=" + nota +
                ", dataLancamento=" + dataLancamento
                ;
    }
}

package services;

public interface ICoversorDados {
    <T> T obterDados(String json, Class<T> classe);
}

package br.com.codenation.domain;

public interface JogadorRepository {

    public Jogador ofId(Long id);

    public void add(Jogador jogador);

    Jogador[] findTop(Integer top);
}

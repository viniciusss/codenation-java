package br.com.codenation.domain;

import br.com.codenation.desafio.exceptions.CapitaoNaoInformadoException;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class Time {
    private Long id;
    private String nome;
    private LocalDate dataCriacao;
    private String corUniformePrincipal;
    private String corUniformeSecundario;
    private List<Jogador> jogadores;

    public Time(Long id, String nome, LocalDate dataCriacao, String corUniformePrincipal, String corUniformeSecundario) {
        this.id = id;
        this.nome = nome;
        this.dataCriacao = dataCriacao;
        this.corUniformePrincipal = corUniformePrincipal;
        this.corUniformeSecundario = corUniformeSecundario;
        this.jogadores = new ArrayList<Jogador>();
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public LocalDate getDataCriacao() {
        return dataCriacao;
    }

    public String getCorUniformePrincipal() {
        return corUniformePrincipal;
    }

    public String getCorUniformeSecundario() {
        return corUniformeSecundario;
    }

    public List<Jogador> getJogadores() {
        return jogadores;
    }

    public void addJogador(Jogador jogador) {
        this.jogadores.add(jogador);
    }

    public Jogador getJogador(Long jogadorId) {
        Predicate<Jogador> byId = Jogador -> Jogador.getId().equals(jogadorId);
        Stream<Jogador> jogadorStream = this.jogadores.stream().filter(byId);
        return null;
    }

    private Jogador[] findBy(Predicate<Jogador> by) {
        return jogadores.stream().filter(by).toArray(Jogador[]::new);
    }

    private Jogador sortJogadorBy(Comparator<Jogador> by) {
        Optional<Jogador> result = jogadores.stream().sorted(by).findFirst();
        return result.orElse(null);
    }

    public Jogador getCapitao() {
        Jogador[] result = this.findBy(Jogador::isCapitao);

        if (0 == result.length) {
            return null;
        }

        return result[0];
    }

    public String getCorCamisaTimeDeFora(Time timeDeFora) {
        if (corUniformePrincipal.equals(timeDeFora.getCorUniformePrincipal())) {
            return timeDeFora.getCorUniformeSecundario();
        }

        return timeDeFora.getCorUniformePrincipal();
    }

    public Jogador getJogadorMaiorSalario() {
        return this.sortJogadorBy(Comparator.comparing(Jogador::getSalario)
                .reversed()
                .thenComparing(Jogador::getId));
    }

    public Jogador getMelhorJogador() {
        return this.sortJogadorBy(Comparator.comparing(Jogador::getNivelHabilidade)
                .reversed()
                .thenComparing(Jogador::getId));
    }

    public Jogador getJogadorMaisVelho() {
        return this.sortJogadorBy(Comparator.comparing(Jogador::getDataNascimento));
    }
}
package br.com.codenation.domain;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Jogador {
    private Long id;
    private String nome;
    private LocalDate dataNascimento;
    private Integer nivelHabilidade;
    private BigDecimal salario;
    private Time timeAtual;
    private Boolean capitao = false;

    public Jogador(Long id, String nome, LocalDate dataNascimento, Integer nivelHabilidade, BigDecimal salario) {
        this.id = id;
        this.nome = nome;
        this.dataNascimento = dataNascimento;
        this.nivelHabilidade = nivelHabilidade;
        this.salario = salario;
    }

    public Jogador(Long id, String nome, LocalDate dataNascimento, Integer nivelHabilidade, BigDecimal salario, Time timeAtual) {
        this.id = id;
        this.nome = nome;
        this.dataNascimento = dataNascimento;
        this.nivelHabilidade = nivelHabilidade;
        this.salario = salario;
        this.timeAtual = timeAtual;
        this.timeAtual.addJogador(this);
    }

    public void definirComoCapitao() {
        Jogador capitaoAtual = timeAtual.getCapitao();
        if (null != capitaoAtual) {
            capitaoAtual.deixarDeSerCapitao();
        }

        this.capitao = true;
    }

    private void deixarDeSerCapitao() {
        capitao = false;
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public Integer getNivelHabilidade() {
        return nivelHabilidade;
    }

    public BigDecimal getSalario() {
        return salario;
    }

    public boolean isCapitao() {
        return capitao;
    }

    public Time getTimeAtual() {
        return timeAtual;
    }

    public void setTimeAtual(Time timeAtual) {
        this.timeAtual = timeAtual;
        this.timeAtual.addJogador(this);
    }
}
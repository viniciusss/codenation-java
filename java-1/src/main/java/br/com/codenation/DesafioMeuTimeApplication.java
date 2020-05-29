package br.com.codenation;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import br.com.codenation.desafio.annotation.Desafio;
import br.com.codenation.desafio.app.MeuTimeInterface;
import br.com.codenation.desafio.exceptions.CapitaoNaoInformadoException;
import br.com.codenation.domain.*;
import br.com.codenation.infra.InMemoryJogadorRepository;
import br.com.codenation.infra.InMemoryTimeRepository;

public class DesafioMeuTimeApplication implements MeuTimeInterface {

    private TimeRepository timeRepository;

    private JogadorRepository jogadorRepository;

    public DesafioMeuTimeApplication() {
        this.timeRepository = new InMemoryTimeRepository();
        this.jogadorRepository = new InMemoryJogadorRepository();
    }

    public void setRepositories(TimeRepository timeRepository, JogadorRepository jogadorRepository) {
        this.timeRepository = timeRepository;
        this.jogadorRepository = jogadorRepository;
    }

    @Desafio("incluirTime")
    public void incluirTime(Long id, String nome, LocalDate dataCriacao, String corUniformePrincipal, String corUniformeSecundario) {
        Time novoTime = new Time(id, nome, dataCriacao, corUniformePrincipal, corUniformeSecundario);
        this.timeRepository.add(novoTime);
    }

    @Desafio("incluirJogador")
    public void incluirJogador(Long id, Long idTime, String nome, LocalDate dataNascimento, Integer nivelHabilidade, BigDecimal salario) {
        Time time = timeRepository.ofId(idTime);
        Jogador novoJogador = new Jogador(id, nome, dataNascimento, nivelHabilidade, salario);
        jogadorRepository.add(novoJogador);
        novoJogador.setTimeAtual(time);
    }

    @Desafio("definirCapitao")
    public void definirCapitao(Long idJogador) {
        Jogador jogador = jogadorRepository.ofId(idJogador);
        jogador.definirComoCapitao();
    }

    @Desafio("buscarCapitaoDoTime")
    public Long buscarCapitaoDoTime(Long idTime) {
        Time time = timeRepository.ofId(idTime);

        Jogador capitao = time.getCapitao();

        if (null == capitao) {
            throw new CapitaoNaoInformadoException();
        }

        return capitao.getId();
    }

    @Desafio("buscarNomeJogador")
    public String buscarNomeJogador(Long idJogador) {
        Jogador jogador = jogadorRepository.ofId(idJogador);
        return jogador.getNome();
    }

    @Desafio("buscarNomeTime")
    public String buscarNomeTime(Long idTime) {
        Time time = timeRepository.ofId(idTime);
        return time.getNome();
    }

    @Desafio("buscarJogadoresDoTime")
    public List<Long> buscarJogadoresDoTime(Long idTime) {
        Time time = timeRepository.ofId(idTime);
        List<Jogador> jogadores = time.getJogadores();

        List<Long> list = new ArrayList<>();
        for (Jogador jogador : jogadores) {
            list.add(jogador.getId());
        }
        return list;
    }

    @Desafio("buscarMelhorJogadorDoTime")
    public Long buscarMelhorJogadorDoTime(Long idTime) {
        Time time = timeRepository.ofId(idTime);
        return time.getMelhorJogador().getId();
    }

    @Desafio("buscarJogadorMaisVelho")
    public Long buscarJogadorMaisVelho(Long idTime) {
        Time time = timeRepository.ofId(idTime);
        return time.getJogadorMaisVelho().getId();
    }

    @Desafio("buscarTimes")
    public List<Long> buscarTimes() {
        List<Long> times = new ArrayList<>();

        for (Time time : timeRepository.all()) {
            times.add(time.getId());
        }

        return times;
    }

    @Desafio("buscarJogadorMaiorSalario")
    public Long buscarJogadorMaiorSalario(Long idTime) {
        Time time = timeRepository.ofId(idTime);
        return time.getJogadorMaiorSalario().getId();
    }

    @Desafio("buscarSalarioDoJogador")
    public BigDecimal buscarSalarioDoJogador(Long idJogador) {
        Jogador jogador = jogadorRepository.ofId(idJogador);
        return jogador.getSalario();
    }

    @Desafio("buscarTopJogadores")
    public List<Long> buscarTopJogadores(Integer top) {
        List<Long> jogadores = new ArrayList<>();

        for (Jogador jogador : jogadorRepository.findTop(top)) {
            jogadores.add(jogador.getId());
        }

        return jogadores;
    }

    @Desafio("buscarCorCamisaTimeDeFora")
    public String buscarCorCamisaTimeDeFora(Long timeDaCasa, Long timeDeFora) {
        Time timeDentroCasa = timeRepository.ofId(timeDaCasa);
        Time timeForaDeCasa = timeRepository.ofId(timeDeFora);
        return timeDentroCasa.getCorCamisaTimeDeFora(timeForaDeCasa);
    }

}

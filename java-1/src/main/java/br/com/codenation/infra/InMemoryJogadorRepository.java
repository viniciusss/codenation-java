package br.com.codenation.infra;

import br.com.codenation.desafio.exceptions.IdentificadorUtilizadoException;
import br.com.codenation.desafio.exceptions.JogadorNaoEncontradoException;
import br.com.codenation.domain.Jogador;
import br.com.codenation.domain.JogadorRepository;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class InMemoryJogadorRepository implements JogadorRepository {
    private final List<Jogador> jogadorList = new ArrayList<Jogador>();

    private Jogador[] findBy(Predicate<Jogador> by) {
        return jogadorList.stream().filter(by).toArray(Jogador[]::new);
    }

    @Override
    public Jogador ofId(Long id) {
        Jogador jogador = find(id);

        if (null == jogador) {
            throw new JogadorNaoEncontradoException();
        }

        return jogador;
    }

    private Jogador find(Long id) {
        Predicate<Jogador> byId = jogador -> jogador.getId().equals(id);

        Jogador[] jogador = findBy(byId);

        if (0 == jogador.length) {
            return null;
        }

        return jogador[0];
    }

    @Override
    public void add(Jogador jogador) {
        Jogador jogadorExistente = find(jogador.getId());

        if (null != jogadorExistente) {
            throw new IdentificadorUtilizadoException();
        }

        jogadorList.add(jogador);
    }

    @Override
    public Jogador[] findTop(Integer top) {
        Stream<Jogador> topJogadores = jogadorList.stream().sorted(Comparator.comparing(Jogador::getNivelHabilidade).reversed());
        return topJogadores.limit(top).toArray(Jogador[]::new);
    }
}

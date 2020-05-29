package br.com.codenation.infra;

import br.com.codenation.desafio.exceptions.IdentificadorUtilizadoException;
import br.com.codenation.desafio.exceptions.TimeNaoEncontradoException;
import br.com.codenation.domain.Jogador;
import br.com.codenation.domain.Time;
import br.com.codenation.domain.TimeRepository;
import br.com.codenation.domain.exceptions.TimeJaExisteException;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class InMemoryTimeRepository implements TimeRepository {

    private final List<Time> timeList = new ArrayList<Time>();

    @Override
    public Time ofId(Long timeId) {
        Time time = find(timeId);

        if (null == time) {
            throw new TimeNaoEncontradoException();
        }

        return time;
    }

    private Time find(Long id) {
        Predicate<Time> byId = time -> time.getId().equals(id);

        Time[] result = findBy(byId);

        if (0 == result.length) {
            return null;
        }

        return result[0];
    }

    private Time[] findBy(Predicate<Time> by) {
        return timeList.stream().filter(by).toArray(Time[]::new);
    }

    @Override
    public void add(Time time) {
        Time timeExistente = find(time.getId());

        if (null != timeExistente) {
            throw new IdentificadorUtilizadoException("O id " + time.getId() + " Ja esta cadastrado para o time: " + time.getNome());
        }

        timeList.add(time);
    }

    @Override
    public Time[] all() {
        return timeList.toArray(new Time[0]);
    }
}

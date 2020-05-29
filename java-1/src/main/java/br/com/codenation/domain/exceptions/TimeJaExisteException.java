package br.com.codenation.domain.exceptions;

import br.com.codenation.desafio.exceptions.IdentificadorUtilizadoException;
import br.com.codenation.domain.Time;

public class TimeJaExisteException extends IdentificadorUtilizadoException {
    private TimeJaExisteException(String s) {
        super(s);
    }

    public static void timeExistente(Time timeExistente) {
        throw new TimeJaExisteException(
                "O id " + timeExistente.getId() + " Ja esta cadastrado para o time: " + timeExistente.getNome()
        );
    }
}

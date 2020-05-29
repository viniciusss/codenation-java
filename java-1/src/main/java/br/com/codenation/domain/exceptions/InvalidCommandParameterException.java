package br.com.codenation.domain.exceptions;

import java.security.InvalidParameterException;
import java.util.List;

public class InvalidCommandParameterException extends InvalidParameterException {
    public InvalidCommandParameterException(String s) {
        super(s);
    }

    public static void nullCommand(Class<?> command) {
        throw new InvalidCommandParameterException(
                "O comando nao pode ser null. Command: " + command
        );
    }

    public static void invalidParameters(Class<?> command, List<String> parameters) {

        throw new InvalidCommandParameterException(
                "Os parametros " +
                        String.join(",", parameters) +
                        " sao invalidos para o comando: " + command
        );
    }

}

package br.com.codenation.infra;

import br.com.codenation.desafio.exceptions.IdentificadorUtilizadoException;
import br.com.codenation.desafio.exceptions.TimeNaoEncontradoException;
import br.com.codenation.domain.Time;
import org.junit.Assert;
import org.junit.Test;

import java.time.LocalDate;

public class InMemoryTimeRepositoryTest {

    @Test
    public void testIdNaoEncontradoDeveLancarException() {
        InMemoryTimeRepository repository = new InMemoryTimeRepository();

        Assert.assertThrows(
                TimeNaoEncontradoException.class,
                () -> repository.ofId(1L)
        );
    }

    @Test
    public void testOfIdDeveRetornarTime() {
        InMemoryTimeRepository repository = new InMemoryTimeRepository();
        Time teste = new Time(1L, "Teste", LocalDate.of(2001, 5, 10), "Branco", "Teste");
        repository.add(teste);

        Assert.assertSame(teste, repository.ofId(1L));
    }

    @Test
    public void testAddDeveAumentarItensDaCollection() {
        InMemoryTimeRepository repository = new InMemoryTimeRepository();
        Time[] times = new Time[2];

        times[0] = new Time(1L, "Teste", LocalDate.of(2001, 5, 10), "Branco", "Teste");
        times[1] = new Time(2L, "ABCD", LocalDate.of(2001, 5, 10), "Branco", "Teste");
        repository.add(times[0]);
        repository.add(times[1]);

        Assert.assertArrayEquals(times, repository.all());
        Assert.assertSame(2, repository.all().length);
    }

    @Test
    public void testAddTimeQueJaExisteDeveLancarException() {

        InMemoryTimeRepository repository = new InMemoryTimeRepository();
        Time[] times = new Time[1];

        times[0] = new Time(1L, "Teste", LocalDate.of(2001, 5, 10), "Branco", "Teste");

        repository.add(times[0]);

        IdentificadorUtilizadoException thrown = Assert.assertThrows(
                IdentificadorUtilizadoException.class,
                () -> repository.add(times[0])
        );

        Assert.assertEquals("O id 1 Ja esta cadastrado para o time: Teste", thrown.getMessage());
    }
}

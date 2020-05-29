package br.com.codenation;

import br.com.codenation.desafio.exceptions.CapitaoNaoInformadoException;
import br.com.codenation.desafio.exceptions.JogadorNaoEncontradoException;
import br.com.codenation.desafio.exceptions.TimeNaoEncontradoException;
import br.com.codenation.domain.Jogador;
import br.com.codenation.domain.JogadorRepository;
import br.com.codenation.domain.Time;
import br.com.codenation.domain.TimeRepository;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

import static org.mockito.Mockito.*;

import java.math.BigDecimal;
import java.time.LocalDate;

public class DesafioMeuTimeApplicationTest {

    @Test
    public void testIncluirTimeDeveChamarSave() {
        TimeRepository timeRepository = mock(TimeRepository.class);
        JogadorRepository jogadorRepository = mock(JogadorRepository.class);

        DesafioMeuTimeApplication application = new DesafioMeuTimeApplication();
        application.setRepositories(timeRepository, jogadorRepository);

        application.incluirTime(1L, "Teste", LocalDate.now(), "Branco", "Verde");

        verify(timeRepository, times(1)).add(any(Time.class));
    }

    @Test
    public void testIncluirJogadorDeveChamarOfIdESave() {
        TimeRepository timeRepository = mock(TimeRepository.class);
        JogadorRepository jogadorRepository = mock(JogadorRepository.class);
        DesafioMeuTimeApplication application = new DesafioMeuTimeApplication();
        application.setRepositories(timeRepository, jogadorRepository);

        when(timeRepository.ofId(1L)).thenReturn(mock(Time.class));

        application.incluirJogador(1L, 1L, "Teste", LocalDate.now(), 100, BigDecimal.valueOf(100));

        verify(jogadorRepository, times(1)).add(any(Jogador.class));
    }

    @Test
    public void testBuscarCapitaoTime() {
        DesafioMeuTimeApplication application = new DesafioMeuTimeApplication();
        application.incluirTime(1L, "Fla", LocalDate.now(), "Azul", "Verde");
        application.incluirJogador(1L, 1L, "Teste", LocalDate.now(), 100, BigDecimal.valueOf(100));

        TimeNaoEncontradoException timeNaoEncontradoException = Assert.assertThrows(
                TimeNaoEncontradoException.class,
                () -> application.buscarCapitaoDoTime(99999L)
        );

        CapitaoNaoInformadoException exception = Assert.assertThrows(
                CapitaoNaoInformadoException.class,
                () -> application.buscarCapitaoDoTime(1L)
        );

        application.definirCapitao(1L);
        Assert.assertSame(1L, application.buscarCapitaoDoTime(1L));
    }

    @Test
    public void testDefinirCapitao() {
        DesafioMeuTimeApplication application = new DesafioMeuTimeApplication();
        application.incluirTime(1L, "Fla", LocalDate.now(), "Azul", "Verde");
        application.incluirJogador(1L, 1L, "Teste", LocalDate.now(), 100, BigDecimal.valueOf(100));
        application.incluirJogador(2L, 1L, "Teste", LocalDate.now(), 100, BigDecimal.valueOf(100));
        application.incluirJogador(3L, 1L, "Teste", LocalDate.now(), 100, BigDecimal.valueOf(100));

        application.definirCapitao(2L);
        application.definirCapitao(1L);
        application.definirCapitao(3L);

        JogadorNaoEncontradoException jogadorNaoEncontradoException = Assert.assertThrows(
                JogadorNaoEncontradoException.class,
                () -> application.definirCapitao(999L)
        );
    }

    @Test
    public void testBuscarNomeJogadorDeveLancarExceptionQuandoNaoEncontrar() {
        DesafioMeuTimeApplication application = new DesafioMeuTimeApplication();

        JogadorNaoEncontradoException jogadorNaoEncontradoException = Assert.assertThrows(
                JogadorNaoEncontradoException.class,
                () -> application.buscarNomeJogador(121312L)
        );
    }

    @Test
    public void testBuscarNomeJogadorDeveRetornarNome() {
        TimeRepository timeRepository = mock(TimeRepository.class);
        JogadorRepository jogadorRepository = mock(JogadorRepository.class);
        DesafioMeuTimeApplication application = new DesafioMeuTimeApplication();
        application.setRepositories(timeRepository, jogadorRepository);

        Jogador jogador = mock(Jogador.class);
        when(jogador.getNome()).thenReturn("Fulano");

        when(jogadorRepository.ofId(1L)).thenReturn(jogador);

        Assert.assertEquals("Fulano", application.buscarNomeJogador(1L));
    }
}
package br.com.codenation.domain;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import java.math.BigDecimal;
import java.time.LocalDate;

public class JogadorTest {

    @Test
    public void testNovoJogador() {
        LocalDate dataNascimento = LocalDate.of(1993, 5, 10);
        BigDecimal salario = BigDecimal.valueOf(1000);
        Time timeAtual = Mockito.mock(Time.class);
        Jogador fulano = new Jogador(1L, "Vinicius", dataNascimento, 50, salario, timeAtual);
        Assert.assertSame(1L, fulano.getId());
        Assert.assertEquals("Vinicius", fulano.getNome());
        Assert.assertSame(dataNascimento, fulano.getDataNascimento());
        Assert.assertSame(50, fulano.getNivelHabilidade());
        Assert.assertSame(salario, fulano.getSalario());
        Assert.assertFalse(fulano.isCapitao());
        Assert.assertSame(timeAtual, fulano.getTimeAtual());
    }

}

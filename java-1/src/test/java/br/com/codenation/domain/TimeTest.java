package br.com.codenation.domain;

import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;
import java.time.LocalDate;

public class TimeTest {

    @Test
    public void testNovoTime() {
        LocalDate criacao = LocalDate.of(1985, 11, 17);
        Time flamengo = new Time(1L, "Flamengo", criacao, "Rubronegro", "Branco");
        Assert.assertSame(1L, flamengo.getId());
        Assert.assertEquals("Flamengo", flamengo.getNome());
        Assert.assertEquals("Rubronegro", flamengo.getCorUniformePrincipal());
        Assert.assertEquals("Branco", flamengo.getCorUniformeSecundario());
        Assert.assertSame(criacao, flamengo.getDataCriacao());
    }

    @Test
    public void testAddJogadorDeveAdicionarJogadorALista() {
        LocalDate criacao = LocalDate.of(1985, 11, 17);
        Time flamengo = new Time(1L, "Flamengo", criacao, "Rubronegro", "Branco");
        Jogador gabigol = new Jogador(1L, "Gagigol", LocalDate.of(1993, 5, 10), 100, BigDecimal.valueOf(100000.00), flamengo);

        Assert.assertEquals(1, flamengo.getJogadores().size());
        Assert.assertSame(flamengo, gabigol.getTimeAtual());
    }

    @Test
    public void testGetCapitaoDeveRetornarCapitaoCasoCapitaoTenhaSidoDefinido() {
        Time bla = new Time(1L, "Bla", LocalDate.now(), "Branco", "Azul");
        Jogador grandeCapitao = new Jogador(1L, "Nao sei", LocalDate.now(), 75, BigDecimal.valueOf(1000), bla);
        grandeCapitao.definirComoCapitao();
        Assert.assertSame(grandeCapitao, bla.getCapitao());
    }

    @Test
    public void testGetCorCamisaTimeDeForaDeveRetornarCorPrincipalDoTimeDeForaCasoSejamDiferentes() {
        Time timeDeCasa = new Time(1L, "Branco/Azul", LocalDate.now(), "Branco", "Azul");
        Time timeDeFora = new Time(2L, "Azul/Branco", LocalDate.now(), "Azul", "Branco");
        Assert.assertEquals("Azul", timeDeCasa.getCorCamisaTimeDeFora(timeDeFora));
    }

    @Test
    public void testGetCorCamisaTimeDeForaDeveRetornarCorSecundariaDoTimeDeForaCasoSejamIguais() {
        Time timeDeCasa = new Time(1L, "Branco/Azul", LocalDate.now(), "Branco", "Azul");
        Time timeDeFora = new Time(2L, "Azul/Branco", LocalDate.now(), "Branco", "Azul");
        Assert.assertEquals("Azul", timeDeCasa.getCorCamisaTimeDeFora(timeDeFora));
    }

    @Test
    public void testGetJogadorMaiorSalario() {
        Time bla = new Time(1L, "Bla", LocalDate.now(), "Branco", "Azul");
        Jogador jogadorA = new Jogador(1L, "Nao sei", LocalDate.now(), 75, BigDecimal.valueOf(100), bla);
        Jogador jogadorB = new Jogador(2L, "Nao sei", LocalDate.now(), 75, BigDecimal.valueOf(105), bla);
        Jogador jogadorC = new Jogador(3L, "Nao sei", LocalDate.now(), 75, BigDecimal.valueOf(105), bla);

        Assert.assertSame(jogadorB, bla.getJogadorMaiorSalario());
    }

    @Test
    public void testGetMelhorJogador() {
        Time bla = new Time(1L, "Bla", LocalDate.now(), "Branco", "Azul");
        Jogador jogadorA = new Jogador(1L, "Nao sei", LocalDate.now(), 550, BigDecimal.valueOf(100), bla);
        Jogador jogadorB = new Jogador(2L, "Nao sei", LocalDate.now(), 201, BigDecimal.valueOf(105), bla);
        Jogador jogadorC = new Jogador(3L, "Nao sei", LocalDate.now(), 78, BigDecimal.valueOf(105), bla);

        Assert.assertSame(jogadorA, bla.getMelhorJogador());
    }


    @Test
    public void testGetJogadorMaisVelho() {
        Time bla = new Time(1L, "Bla", LocalDate.now(), "Branco", "Azul");
        Jogador jogadorA = new Jogador(1L, "Nao sei", LocalDate.of(1993, 6, 10), 550, BigDecimal.valueOf(100), bla);
        Jogador jogadorB = new Jogador(2L, "Nao sei", LocalDate.of(1993, 5, 10), 201, BigDecimal.valueOf(105), bla);
        Jogador jogadorC = new Jogador(3L, "Nao sei", LocalDate.of(2000, 5, 10), 78, BigDecimal.valueOf(105), bla);

        Assert.assertSame(jogadorB, bla.getJogadorMaisVelho());
    }
}

package br.com.codenation.calculadora;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

public class CalculadoraSalarioTest {

	@Test
	public void salarioLiquidoIsNotNull() {
		assertNotNull(new CalculadoraSalario().calcularSalarioLiquido(1000.0));
	}
	
	@Test
	public void salariosLiquidoExemplo() {

		CalculadoraSalario calculador = new CalculadoraSalario();

		assertEquals(1365.00, calculador.calcularSalarioLiquido(1500.01), 2);
		assertEquals(7565.00, calculador.calcularSalarioLiquido(10000.0), 2);
		assertEquals(1380.00, calculador.calcularSalarioLiquido(1500.0), 2);
		assertEquals(4940.00, calculador.calcularSalarioLiquido(6000.0), 2);
	}

}
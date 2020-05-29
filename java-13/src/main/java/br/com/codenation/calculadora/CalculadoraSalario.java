package br.com.codenation.calculadora;

public class CalculadoraSalario {

	private double salarioMinimo = 1039;

	public static void main(String[] args) {
		CalculadoraSalario calculador = new CalculadoraSalario();

		System.out.println(calculador.calcularSalarioLiquido(1500.01));
		//assertEquals(7565.00, calculador.calcularSalarioLiquido(10000.0), 2);
	}

	public long calcularSalarioLiquido(double salarioBase) {

		if (salarioMinimo >= salarioBase) {
			return 0;
		}

		salarioBase -= calcularInss(salarioBase);
		salarioBase -= calcularIrrf(salarioBase);

		return Math.round(salarioBase);
	}
	
	
	//Exemplo de método que pode ser criado para separar melhor as responsábilidades de seu algorítmo
	private double calcularInss(double salarioBase) {
		double percentualDesconto = 8;

		if (4000 <= salarioBase) {
			percentualDesconto = 11;
		} else if (1500.01 <= salarioBase) {
			percentualDesconto = 9;
		}

		return salarioBase*(percentualDesconto/100);
	}

	private double calcularIrrf(double salarioBase) {
		if (salarioBase <= 3000) {
			return 0;
		}

		double percentualDesconto = 7.5;

		if (6000 <= salarioBase) {
			percentualDesconto = 15;
		}

		return salarioBase*(percentualDesconto/100);
	}
	
}
/*Dúvidas ou Problemas?
Manda e-mail para o meajuda@codenation.dev que iremos te ajudar! 
*/
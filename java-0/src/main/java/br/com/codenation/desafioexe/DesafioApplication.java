package br.com.codenation.desafioexe;

import java.util.ArrayList;
import java.util.List;

public class DesafioApplication {

	public static List<Integer> fibonacci() {

		Integer qtdeNumeros = 350;

		ArrayList<Integer> numerosList = new ArrayList<Integer>();


		numerosList.add(0); // fibonacci de 1
		numerosList.add(1); // fibonacci de 2

		do {
			Integer a = numerosList.get(numerosList.size()-1);
			Integer b = numerosList.get(numerosList.size()-2);

			numerosList.add(
				Integer.sum(a, b)
			);
		} while (numerosList.get(numerosList.size()-1) < qtdeNumeros);

		return numerosList;
	}

	public static Boolean isFibonacci(Integer a) {
		return fibonacci().contains(a);
	}

}
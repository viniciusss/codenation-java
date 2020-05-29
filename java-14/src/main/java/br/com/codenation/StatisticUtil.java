package br.com.codenation;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class StatisticUtil {

	public static int average(int[] elements) {
		int sum = Arrays.stream(elements).sum();
		return sum / elements.length;
	}

	public static int mode(int[] elements) {
		HashMap<Integer, Integer> elementosAgrupados = new HashMap<Integer, Integer>();

		System.out.println("mode:");
		System.out.println(Arrays.toString(elements));

		for (int element : elements) {
			int qtdeOcorrencias = 0;

			if (elementosAgrupados.containsKey(element)) {
				qtdeOcorrencias = (int) elementosAgrupados.get(element);
			}

			elementosAgrupados.put(element, ++qtdeOcorrencias);
		}

		AtomicInteger maiorRepeticao = new AtomicInteger();
		AtomicInteger moda = new AtomicInteger();

		elementosAgrupados.forEach((numero, qtdeRepeticoes) -> {
			if (qtdeRepeticoes > maiorRepeticao.get()) {
				maiorRepeticao.set(qtdeRepeticoes);
				moda.set(numero);
			}
		});

		return moda.get();
	}

	public static int median(int[] elements) {
		int[] orderedElements = Arrays.stream(elements).sorted().toArray();

		int posicaoMediana = (orderedElements.length)/2;
		int mediana = orderedElements[posicaoMediana];

		if (orderedElements.length % 2 == 0) {
			mediana = orderedElements[posicaoMediana-1] + mediana;
			mediana =  mediana/2;
		}

		return mediana;
	}
}
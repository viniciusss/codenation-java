package com.challenge;

import com.challenge.annotation.Somar;
import com.challenge.annotation.Subtrair;
import com.challenge.desafio.CalculadorDeClasses;
import com.challenge.interfaces.Calculavel;

import java.math.BigDecimal;

public class main {
    public static void main(String[] args) {
        Calculavel calculador = new CalculadorDeClasses();
        try {
            System.out.println(calculador.somar(new local()));
            System.out.println(calculador.subtrair(new local()));
            System.out.println(calculador.totalizar(new local()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

class local {
    @Somar
    private BigDecimal a = BigDecimal.valueOf(1000);

    @Somar
    private BigDecimal b = BigDecimal.valueOf(2000);

    @Subtrair
    private BigDecimal c = BigDecimal.valueOf(222);
}

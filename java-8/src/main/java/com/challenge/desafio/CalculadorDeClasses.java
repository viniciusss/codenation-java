package com.challenge.desafio;

import com.challenge.annotation.Somar;
import com.challenge.annotation.Subtrair;
import com.challenge.interfaces.Calculavel;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class CalculadorDeClasses implements Calculavel {
    @Override
    public BigDecimal somar(Object object) throws Exception {
        return calcular(object, Somar.class);
    }

    @Override
    public BigDecimal subtrair(Object object) throws Exception {
        return calcular(object, Subtrair.class);
    }

    private BigDecimal calcular(Object object, Class<? extends Annotation> annotationClass) throws IllegalAccessException {
        List<BigDecimal> valoresSomar = new ArrayList<BigDecimal>();

        Class<?> clazz = object.getClass();
        for (Field field : clazz.getDeclaredFields()) {
            field.setAccessible(true);
            if (field.isAnnotationPresent(annotationClass)) {
                valoresSomar.add((BigDecimal) field.get(object));
            }
        }

        return valoresSomar.stream().reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    @Override
    public BigDecimal totalizar(Object object) throws Exception {
        BigDecimal somar = somar(object);
        BigDecimal subtrair = subtrair(object);

        return somar.subtract(subtrair);
    }
}

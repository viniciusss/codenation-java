package com.challenge.interfaces;

import java.math.BigDecimal;

public interface Calculavel {
    public BigDecimal somar(Object object) throws Exception;
    public BigDecimal subtrair(Object object) throws Exception;
    public BigDecimal totalizar(Object object) throws Exception;
}

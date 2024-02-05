package com.br.financas.util;

import com.br.financas.entity.Transacao;
import org.springframework.data.jpa.domain.Specification;

import java.math.BigDecimal;

public class TransacaoSpecifications {

    public static Specification<Transacao> precoMaiorOuIgual(BigDecimal valorMin) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.greaterThanOrEqualTo(root.get("valor"), valorMin);
    }

    public static Specification<Transacao> precoMenorOuIgual(BigDecimal valorMax) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.lessThanOrEqualTo(root.get("preco"), valorMax);
    }
}

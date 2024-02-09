package com.br.financas.util;

import com.br.financas.entity.Transacao;
import org.springframework.data.jpa.domain.Specification;

import java.math.BigDecimal;

public class TransacaoSpecifications {

    public static Specification<Transacao> precoMaiorOuIgual(BigDecimal valorMin) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.greaterThanOrEqualTo(root.get("valorTotal"), valorMin);
    }

    public static Specification<Transacao> precoMenorOuIgual(BigDecimal valorMax) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.lessThanOrEqualTo(root.get("valorTotal"), valorMax);
    }

    public static Specification<Transacao> descricaoContemIgnoreCase(String descricao) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.like(
                        criteriaBuilder.lower(root.get("descricao")),
                        "%" + descricao.toLowerCase() + "%"
                );
    }
}


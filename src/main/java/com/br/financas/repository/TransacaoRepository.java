package com.br.financas.repository;

import com.br.financas.entity.Transacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface TransacaoRepository extends JpaRepository<Transacao, Long> {
    List<Transacao> findByDescricaoContainingIgnoreCase(String descricao);
    List<Transacao> findByDateBetween(Date dataInicio, Date dataFim);

    List<Transacao> findByValorTotalGreaterThanOrderByValorTotal(Double valor);

    List<Transacao> findByValorTotalLessThanOrderByValorTotal(Double valor);
}

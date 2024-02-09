package com.br.financas.repository;

import com.br.financas.entity.Transacao;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface TransacaoRepository extends JpaRepository<Transacao, Long> {

    List<Transacao> findByDateBetween(Date dataInicio, Date dataFim);

    List<Transacao> findAll(Specification<Transacao> specification, Sort sort);
}

package com.br.financas.service;

import com.br.financas.entity.Transacao;
import com.br.financas.enums.TipoTransacao;
import com.br.financas.repository.TransacaoRepository;
import com.br.financas.util.TransacaoSpecifications;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class TransacaoService {
    private final TransacaoRepository transacaoRepository;

    public TransacaoService(TransacaoRepository transacaoRepository) {
        this.transacaoRepository = transacaoRepository;
    }

    public List<Transacao> getAllTransacao() {
        List<Transacao> transacoes = transacaoRepository.findAll();
        transacoes = transacoes.stream()
                .filter(transacao -> transacao.getDate() != null)
                .collect(Collectors.toList());

        transacoes.sort(Comparator.comparing(Transacao::getDate).reversed());

        return transacoes;
    }


    public void addTransacao(Transacao transacao) {

        transacaoRepository.save(transacao);
    }


    public void excluirTransacao(Long id) {
        transacaoRepository.deleteById(id);
    }

    public List<Transacao> pesquisarPorDescricao(String descricao) {
        Sort sortByDate = Sort.by("date").descending();

        return transacaoRepository.findAll(
                Specification.where(TransacaoSpecifications.descricaoContemIgnoreCase(descricao)),
                sortByDate
        );
    }

    public List<Transacao> pesquisarPorData(Date dataInicio, Date dataFim) {
        return new ArrayList<>(transacaoRepository.findByDateBetween(dataInicio, dataFim));
    }

    public List<Transacao> filtrarPorValor(BigDecimal precoMin, BigDecimal precoMax) {
        Specification<Transacao> specification = Specification.where(null);

        if (precoMin != null) {
            specification = specification.and(TransacaoSpecifications.precoMaiorOuIgual(precoMin));
        }

        if (precoMax != null) {
            specification = specification.and(TransacaoSpecifications.precoMenorOuIgual(precoMax));
        }
        Sort sort = Sort.by(Sort.Direction.DESC, "date");
        return transacaoRepository.findAll(specification,sort);

    }

    public void atualizar(Long id, Transacao transacaoAtualizada) {

        Optional<Transacao> transacaoOptional = transacaoRepository.findById(id);

        if (transacaoOptional.isPresent()) {

            Transacao transacao = transacaoOptional.get();


            if (transacaoAtualizada.getCategoria() != null) {
                transacao.setCategoria(transacaoAtualizada.getCategoria());

            }

            if (transacaoAtualizada.getDescricao() != null) {
                transacao.setDescricao(transacaoAtualizada.getDescricao());
            }

            if (transacaoAtualizada.getTipo() != null) {
                transacao.setTipo(transacaoAtualizada.getTipo());
            }

            transacaoRepository.save(transacao);
        } else {
            System.out.println("Erro");
        }

    }


}

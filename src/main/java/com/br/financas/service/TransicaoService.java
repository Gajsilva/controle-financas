package com.br.financas.service;

import com.br.financas.entity.Transacao;
import com.br.financas.repository.TransacaoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TransicaoService {
    private final TransacaoRepository transacaoRepository;

    public TransicaoService(TransacaoRepository transacaoRepository) {
        this.transacaoRepository = transacaoRepository;
    }

    public List<Transacao> getAllTransacao(){
        List <Transacao> transacaos = transacaoRepository.findAll();
        return transacaos.stream().toList();
    }

    public Transacao addTransacao ( Transacao transacao) {
        return transacaoRepository.save(transacao);
    }

    
}

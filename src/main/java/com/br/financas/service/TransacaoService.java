package com.br.financas.service;

import com.br.financas.entity.Transacao;
import com.br.financas.repository.TransacaoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TransacaoService {
    private final TransacaoRepository transacaoRepository;

    public TransacaoService(TransacaoRepository transacaoRepository) {
        this.transacaoRepository = transacaoRepository;
    }

    public List<Transacao> getAllTransacao(){
        List <Transacao> transacaos = transacaoRepository.findAll();
        return transacaos.stream().toList();
    }
    public void addTransacao (Transacao transacao) {
        transacaoRepository.save(transacao);
    }

    public Transacao obterTransacaoPorId(Long id) {
        return transacaoRepository.findById(id).orElse(null);
    }
    public void excluirTransacao(Long id) {
        transacaoRepository.deleteById(id);
    }
    public List<Transacao> pesquisarPorDescricao(String nome) {

        return transacaoRepository.findByDescricaoContainingIgnoreCase(nome);

    }



    
}

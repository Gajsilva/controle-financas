package com.br.financas.service;

import com.br.financas.entity.Transacao;
import com.br.financas.repository.TransacaoRepository;
import com.br.financas.util.TransacaoSpecifications;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TransacaoService {
    private final TransacaoRepository transacaoRepository;

    public TransacaoService(TransacaoRepository transacaoRepository) {
        this.transacaoRepository = transacaoRepository;
    }

    public List<Transacao> getAllTransacao() {
        List<Transacao> transacaos = transacaoRepository.findAll();
        return transacaos.stream().toList();
    }

    public Transacao findBy(Long id) {
        return transacaoRepository.findById(id).get();
    }

    public void addTransacao(Transacao transacao) {
        transacaoRepository.save(transacao);
    }

    public Transacao obterTransacaoPorId(Long id) {
        return transacaoRepository.findById(id).orElse(null);
    }

    public void excluirTransacao(Long id) {
        transacaoRepository.deleteById(id);
    }

    public List<Transacao> pesquisarPorDescricao(String descricao) {
        return transacaoRepository.findByDescricaoContainingIgnoreCase(descricao)
                .stream()
                .filter(transacao -> {
                    String getDescricao = transacao.getDescricao().toLowerCase();
                    String[] partes = descricao.toLowerCase().split("\\s+");

                    for (String parte : partes) {
                        if (descricao.contains(parte)) {
                            return true;
                        }
                    }
                    return false;
                })
                .collect(Collectors.toList());
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

        return transacaoRepository.findAll((Sort) specification);
    }


    public void atualizar(Long id, Transacao transacaoAtualizada) {
        // Verifica se a transação com o ID fornecido existe no banco de dados
        Optional<Transacao> transacaoOptional = transacaoRepository.findById(id);

        if (transacaoOptional.isPresent()) {
            // Transação encontrada, atualiza os atributos
            Transacao transacao = transacaoOptional.get();

            // Adicione a lógica para verificar e atualizar os atributos conforme necessário
            if (transacaoAtualizada.getCategoria() != null) {
                transacao.setCategoria(transacaoAtualizada.getCategoria());
            }

            if (transacaoAtualizada.getDescricao() != null) {
                transacao.setDescricao(transacaoAtualizada.getDescricao());
            }

            if (transacaoAtualizada.getTipo() != null) {
                transacao.setTipo(transacaoAtualizada.getTipo());
            }

            // Salva a transação atualizada no banco de dados
            transacaoRepository.save(transacao);
        } else {
            // Transação não encontrada, você pode lidar com isso conforme necessário (lançar uma exceção, por exemplo)
        }
    }






}

package com.br.financas.controller;

import com.br.financas.entity.Transacao;
import com.br.financas.service.TransicaoService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TransacaoControler {
    private final TransicaoService transicaoService;

    public TransacaoControler(TransicaoService transicaoService) {
        this.transicaoService = transicaoService;
    }

    @PostMapping(name = "/add-transacao")
    public ResponseEntity<Transacao> addTransacao (@RequestBody Transacao transacao){
        return ResponseEntity.ok(transicaoService.addTransacao(transacao));
    }

    @GetMapping("/transacoes")
    public ResponseEntity<List<Transacao>> transacoes(){
        return ResponseEntity.ok(transicaoService.getAllTransacao());
    }
}

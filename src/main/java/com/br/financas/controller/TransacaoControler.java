package com.br.financas.controller;

import com.br.financas.entity.Transacao;
import com.br.financas.service.TransicaoService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller()
public class TransacaoControler {
    private final TransicaoService transicaoService;

    public TransacaoControler(TransicaoService transicaoService) {
        this.transicaoService = transicaoService;
    }

    @GetMapping("/home")
    public ModelAndView home(){
        ModelAndView mv = new ModelAndView("home");
        mv.addObject("transacao",transacoes());
        return mv;
    }
    @GetMapping("/add-transacao")
    public ModelAndView addTransacaoView(){
        ModelAndView mv = new ModelAndView("add-transacao");
        mv.addObject("transacao", new Transacao());
        return mv;
    }

    @PostMapping(value="/transacao/add", consumes = "application/x-www-form-urlencoded")
    public ModelAndView addTransacao (@RequestBody Transacao transacao){
        Transacao novaTransacao = transicaoService.addTransacao(transacao);
        return new ModelAndView("redirect:/home");
    }

    @GetMapping("/transacoes")
    public ResponseEntity<List<Transacao>> transacoes(){
        return ResponseEntity.ok(transicaoService.getAllTransacao());
    }
}
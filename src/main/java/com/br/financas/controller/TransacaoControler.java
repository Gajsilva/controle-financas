package com.br.financas.controller;

import com.br.financas.entity.Transacao;
import com.br.financas.service.TransacaoService;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@Validated
public class TransacaoControler {
    private final TransacaoService transacaoService;

    public TransacaoControler(TransacaoService transacaoService) {
        this.transacaoService = transacaoService;
    }

    @GetMapping("/index")
    public ModelAndView home(){
        ModelAndView mv = new ModelAndView("index");
        mv.addObject("transacoes", transacaoService.getAllTransacao());
        return mv;
    }
    @GetMapping("/add-transacao")
    public ModelAndView addTransacaoView(){
        ModelAndView mv = new ModelAndView("add-transacao");
        mv.addObject("transacao", new Transacao());
        return mv;
    }
    @GetMapping("/editar/{id}")
    public String editarTransacao(@PathVariable Long id, ModelAndView mv) {
        Transacao transacao = transacaoService.obterTransacaoPorId(id);
        mv.addObject("transacao", transacao);
        return "redirect:/index";
    }
    @GetMapping("/excluir/{id}")
    public String excluirTransacao(@PathVariable Long id) {
        transacaoService.excluirTransacao(id);
        return "redirect:/index";
    }

    @GetMapping("/pesquisar")
    public ModelAndView pesquisarTransacoesPorDescricao(@RequestParam("descricao") String descricao) {
        List<Transacao> transacoes = transacaoService.pesquisarPorDescricao(descricao);
        ModelAndView modelAndView = new ModelAndView("index");
        modelAndView.addObject("transacoes", transacoes);
        return modelAndView;
    }

    @PostMapping(value="/transacao/add", consumes = "application/x-www-form-urlencoded")
    public String addTransacao ( Transacao transacao){
        transacaoService.addTransacao(transacao);
        return "redirect:/index";
    }

}

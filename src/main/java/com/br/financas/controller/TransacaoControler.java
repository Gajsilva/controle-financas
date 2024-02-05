package com.br.financas.controller;

import com.br.financas.entity.Transacao;
import com.br.financas.service.TransacaoService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.math.BigDecimal;
import java.util.Date;
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

    @PostMapping(value = "/editar-transacao/{id}")
    public ModelAndView editarEAtualizarTransacao(@PathVariable Long id, Transacao transacao) {
        ModelAndView modelAndView = new ModelAndView();

        // Adicione a lógica para verificar valores não nulos aqui
        // Certifique-se de validar e tratar valores nulos conforme necessário

        transacaoService.atualizar(id, transacao);

        modelAndView.setViewName("redirect:/index"); // Redireciona para a página desejada após a atualização
        return modelAndView;
    }



    @GetMapping("/transacao/{id}")
    public Transacao obterTransacaoPorId(@PathVariable Long id) {
       return transacaoService.findBy(id);
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
    @GetMapping("/pesquisarPorData")
    public ModelAndView pesquisarTransacoesPorData(
            @RequestParam("dataInicio") @DateTimeFormat(pattern = "yyyy-MM-dd") Date dataInicio,
            @RequestParam("dataFim") @DateTimeFormat(pattern = "yyyy-MM-dd") Date dataFim) {

        List<Transacao> transacoes = transacaoService.pesquisarPorData(dataInicio, dataFim);
        ModelAndView modelAndView = new ModelAndView("index");
        modelAndView.addObject("transacoes", transacoes);
        return modelAndView;
    }

    @GetMapping("/filtrarPorValor")
    public ModelAndView filtrarPorValor(
            @RequestParam("precoMin") BigDecimal precoMin,
            @RequestParam("precoMax") BigDecimal precoMax) {

        List<Transacao> transacoes = transacaoService.filtrarPorValor(precoMin, precoMax);
        ModelAndView modelAndView = new ModelAndView("index");
        modelAndView.addObject("transacoes", transacoes);
        return modelAndView;
    }
}

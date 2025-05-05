package com.example.pessoacrud.controller;

import com.example.pessoacrud.model.Pessoa;
import com.example.pessoacrud.repository.PessoaRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class PessoaController {
    private final PessoaRepository repository;

    public PessoaController(PessoaRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("pessoas", repository.findAll());
        return "index";
    }

    @GetMapping("/nova")
    public String novaPessoa(Model model) {
        model.addAttribute("pessoa", new Pessoa());
        return "form";
    }

    @PostMapping("/salvar")
    public String salvarPessoa(@ModelAttribute Pessoa pessoa) {
        repository.save(pessoa);
        return "redirect:/";
    }

    @GetMapping("/editar/{id}")
    public String editarPessoa(@PathVariable Long id, Model model) {
        model.addAttribute("pessoa", repository.findById(id).orElseThrow());
        return "edit";
    }

    @PostMapping("/atualizar/{id}")
    public String atualizarPessoa(@PathVariable Long id, @ModelAttribute Pessoa pessoa) {
        pessoa.setId(id);
        repository.save(pessoa);
        return "redirect:/";
    }

    @GetMapping("/deletar/{id}")
    public String deletarPessoa(@PathVariable Long id) {
        repository.deleteById(id);
        return "redirect:/";
    }
}

package br.net.daniel.silva.estudo.tdd.estudotdd.controller;

import br.net.daniel.silva.estudo.tdd.estudotdd.models.Pessoa;
import br.net.daniel.silva.estudo.tdd.estudotdd.services.PessoaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pessoa")
public class PessoaController {

    private PessoaService service;

    @Autowired
    public PessoaController (PessoaService service) {
        this.service = service;
    }

    @PostMapping
    public Pessoa create(@RequestBody Pessoa pessoa) {
        return service.create(pessoa);
    }

    @GetMapping
    public List<Pessoa> findAll() {
        return service.findAll();
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void remove(@PathVariable("id") Long id) {
        service.remove(id);
    }
}

package br.net.daniel.silva.estudo.tdd.estudotdd.controller;

import br.net.daniel.silva.estudo.tdd.estudotdd.models.Pessoa;
import br.net.daniel.silva.estudo.tdd.estudotdd.services.PessoaService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(value = "API para estudo do TDD")
@RestController
@RequestMapping("/pessoa")
public class PessoaController {

    private PessoaService service;

    @Autowired
    public PessoaController (PessoaService service) {
        this.service = service;
    }

    @ApiOperation(value = "API para criar Pessoa", consumes = "application/json")
    @PostMapping
    public Pessoa create(@RequestBody Pessoa pessoa) {
        return service.create(pessoa);
    }

    @ApiOperation(value = "API para Listar Pessoas", consumes = "application/json")
    @GetMapping
    public List<Pessoa> findAll() {
        return service.findAll();
    }

    @ApiOperation(value = "API para excluir Pessoa")
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void remove(@PathVariable("id") Long id) {
        service.remove(id);
    }
}

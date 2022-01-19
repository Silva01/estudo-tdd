package br.net.daniel.silva.estudo.tdd.estudotdd.services;

import br.net.daniel.silva.estudo.tdd.estudotdd.models.Pessoa;

import java.util.List;

public interface PessoaService {
    Pessoa create(Pessoa pessoa);
    List<Pessoa> findAll();
    void remove(Long id);
}

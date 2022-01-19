package br.net.daniel.silva.estudo.tdd.estudotdd.services;

import br.net.daniel.silva.estudo.tdd.estudotdd.models.Pessoa;
import br.net.daniel.silva.estudo.tdd.estudotdd.repositors.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PessoaServiceImpl implements PessoaService{

    private PessoaRepository repository;

    @Autowired
    public PessoaServiceImpl(PessoaRepository repository) {
        this.repository = repository;
    }

    @Override
    public Pessoa create(Pessoa pessoa) {
        return repository.save(pessoa);
    }

    @Override
    public List<Pessoa> findAll() {
        return repository.findAll();
    }

    @Override
    public void remove(Long id) {
        repository.delete(repository.getById(id));
    }
}

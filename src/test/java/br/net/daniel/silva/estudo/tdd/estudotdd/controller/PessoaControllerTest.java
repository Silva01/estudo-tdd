package br.net.daniel.silva.estudo.tdd.estudotdd.controller;

import br.net.daniel.silva.estudo.tdd.estudotdd.models.Pessoa;
import br.net.daniel.silva.estudo.tdd.estudotdd.services.PessoaService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = PessoaController.class)
public class PessoaControllerTest {

    @Autowired
    private MockMvc mock;

    @MockBean
    private PessoaService service;

    @Test
    public void deveCadastrarPessoa() throws Exception {
        Pessoa pessoa = new Pessoa("Daniel", 32);
        pessoa.setId(1L);

        String mockPessoasJson = convertToJson(pessoa);

        when(service.create(any(Pessoa.class))).thenReturn(pessoa);

        mock.perform(
                    post("/pessoa")
                    .contentType(MediaType.APPLICATION_JSON)
                    .accept(MediaType.APPLICATION_JSON)
                    .content(mockPessoasJson))
                .andExpect(status().isOk())
                .andExpect(content().json(mockPessoasJson));

        verify(service).create(any(Pessoa.class));
    }

    @Test
    public void deveListarPessoas() throws Exception {
        Pessoa pessoa = new Pessoa("Daniel", 32);
        pessoa.setId(1L);

        List<Pessoa> mockPessoas = Collections.singletonList(pessoa);
        String json = convertToJson(mockPessoas);

        when(service.findAll()).thenReturn(mockPessoas);

        mock.perform(get("/pessoa").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200))
                .andExpect(content().json(json));
    }

    @Test
    public void deveDeletarPessoa() throws Exception {
        mock.perform(delete("/pessoa/{id}", 1))
                .andExpect(status().is(200));
    }

    private String convertToJson(Object obj) throws JsonProcessingException {
        return new ObjectMapper().writer().withDefaultPrettyPrinter().writeValueAsString(obj);
    }
}

package br.net.daniel.silva.estudo.tdd.estudotdd.controller;

import br.net.daniel.silva.estudo.tdd.estudotdd.models.Pessoa;
import br.net.daniel.silva.estudo.tdd.estudotdd.services.PessoaService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Collections;
import java.util.List;

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

        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        String mockPessoasJson = ow.writeValueAsString(pessoa);

        Mockito.when(service.create(Mockito.any(Pessoa.class))).thenReturn(pessoa);

        mock.perform(
                    MockMvcRequestBuilders.post("/pessoa")
                    .contentType(MediaType.APPLICATION_JSON)
                    .accept(MediaType.APPLICATION_JSON)
                    .content(mockPessoasJson))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(mockPessoasJson));

        Mockito.verify(service).create(Mockito.any(Pessoa.class));
    }
}

package br.net.daniel.silva.estudo.tdd.estudotdd.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@Api(value = "API de Teste")
@RestController
public class HelloWordController {

    @ApiOperation(value = "API para Listar Hello Word")
    @GetMapping("/hello")
    @ResponseStatus(HttpStatus.OK)
    public String hello() {
        return "Ola Mundo";
    }
}

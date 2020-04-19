package com.vinireis.renomeararquivos.api;

import java.io.IOException;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.vinireis.renomeararquivos.service.GerenciadorDeArquivosService;

@Controller
public class GerenciadorDeArquivosAPI {
	private GerenciadorDeArquivosService gerenciador;

	public GerenciadorDeArquivosAPI(GerenciadorDeArquivosService gerenciador) {
		this.gerenciador = gerenciador;
	}
	
	@GetMapping(value = "/gerenciador-arquivos/renomear")
    @ResponseStatus(HttpStatus.OK)
	public void renomearArquivos(@Valid @NotNull @RequestParam(value = "diretorio", required = true) String diretorio) throws IOException {
		gerenciador.renomeiaArquivosNo(diretorio);
	}
}

package com.vinireis.renomeararquivos.service;

import java.io.IOException;
import java.nio.file.Path;
import java.util.stream.Stream;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class GerenciadorDeArquivosService {
	private DiretorioService diretorioService;
	private RenomeadorComponent renomeador;

	public GerenciadorDeArquivosService(DiretorioService diretorioService, RenomeadorComponent renomeador) {
		this.diretorioService = diretorioService;
		this.renomeador = renomeador;
	}

	public void renomeiaArquivosNo(@Valid @NotNull String diretorio) throws IOException {
		log.info("Os arquivos do diretório {} serão renomeados!", diretorio);
		Stream<Path> arquivosDoDiretorio = diretorioService.getArquivosDoDiretorio(diretorio);
		arquivosDoDiretorio.forEach(p -> renomeador.renomeia(p,diretorio));
	}
}

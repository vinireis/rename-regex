package com.vinireis.renomeararquivos.service;

import java.nio.file.Path;

import org.springframework.stereotype.Service;

@Service
public class BuilderAluraPDF implements BuilderNomeArquivo {
	private static final String REGEX_PDF_ALURA = ".*_ Aula (\\d{1,2}) - Atividade (\\d{1,2}) (.*) _.*.pdf";

	@Override
	public String getRegex() {
		return REGEX_PDF_ALURA;
	}

	@Override
	public String build(Path antigoNomeArquivo) {
		return new NomeArquivoAlura.Builder()
		.numeroAlura(antigoNomeArquivo.toString().replaceAll(REGEX_PDF_ALURA, "$1"))
		.numeroAtividade(antigoNomeArquivo.toString().replaceAll(REGEX_PDF_ALURA, "$2"))
		.nomeAula(antigoNomeArquivo.toString().replaceAll(REGEX_PDF_ALURA, "$3"))
		.extensao(".pdf")
		.build()
		.toString();
	}
}

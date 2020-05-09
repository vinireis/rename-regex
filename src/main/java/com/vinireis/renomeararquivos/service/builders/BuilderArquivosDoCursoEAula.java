package com.vinireis.renomeararquivos.service.builders;

import java.nio.file.Path;

import org.springframework.stereotype.Service;

@Service
public class BuilderArquivosDoCursoEAula implements BuilderNomeArquivo {
	private static final String REGEX_ARQUIVOS_RENOMEAR = "^\\d{2} \\d{2} -.*";

	@Override
	public String getRegex() {
		return REGEX_ARQUIVOS_RENOMEAR;
	}

	@Override
	public String build(Path antigoNomeArquivo) {
		return antigoNomeArquivo.toString();
	}
}

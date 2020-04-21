package com.vinireis.renomeararquivos.service.builders;

import java.nio.file.Path;

import org.springframework.stereotype.Service;

import com.vinireis.renomeararquivos.service.NomeArquivoAlura;

@Service
public class BuilderAluraVideos implements BuilderNomeArquivo {
	private static final String REGEX_VIDEO_ALURA = ".* Aula (\\d{1,2}) - Atividade (\\d{1,2}) (.*)(.mp4)";

	@Override
	public String getRegex() {
		return REGEX_VIDEO_ALURA;
	}

	@Override
	public String build(Path antigoNomeArquivo) {
		return new NomeArquivoAlura.Builder()
		.numeroAula(antigoNomeArquivo.toString().replaceAll(REGEX_VIDEO_ALURA, "$1"))
		.numeroAtividade(antigoNomeArquivo.toString().replaceAll(REGEX_VIDEO_ALURA, "$2"))
		.nomeAula(antigoNomeArquivo.toString().replaceAll(REGEX_VIDEO_ALURA, "$3"))
		.extensao(antigoNomeArquivo.toString().replaceAll(REGEX_VIDEO_ALURA, "$4"))
		.build()
		.toString();
	}
}

package com.vinireis.renomeararquivos.service.builders;

import java.nio.file.Path;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class BuilderNomeArquivoStrategy {
	private List<BuilderNomeArquivo> listNomeArquivoBuilder;
	
	public BuilderNomeArquivoStrategy(List<BuilderNomeArquivo> listNomeArquivoBuilder) {
		this.listNomeArquivoBuilder = listNomeArquivoBuilder;
	}

	public BuilderNomeArquivo getBuilderNomeArquivoByAntigoNomeArquivo(Path AntigoNomeArquivo) throws IllegalArgumentException {
		return listNomeArquivoBuilder.stream()
		.filter(NomeArquivoBuilder -> NomeArquivoBuilder.matches(AntigoNomeArquivo))
		.findAny()
		.orElseThrow(() -> new IllegalArgumentException());
	}
}

package com.vinireis.renomeararquivos.service;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.stereotype.Service;

import com.vinireis.renomeararquivos.service.builders.BuilderNomeArquivo;
import com.vinireis.renomeararquivos.service.builders.BuilderNomeArquivoStrategy;

@Service
public class PathService {
	private BuilderNomeArquivoStrategy builderNomeArquivoStrategy;
		
	public PathService(BuilderNomeArquivoStrategy builderNomeArquivoStrategy) {
		this.builderNomeArquivoStrategy = builderNomeArquivoStrategy;
	}

	public Path getNovoPath(Path antigoPath) {
		Path diretorioPai = antigoPath.getParent();
		Path antigoNomeArquivo = antigoPath.getFileName();
		BuilderNomeArquivo builderNomeArquivo = builderNomeArquivoStrategy.getBuilderNomeArquivoByAntigoNomeArquivo(antigoNomeArquivo);
		String novoNomeArquivo = builderNomeArquivo.build(antigoNomeArquivo);
		return Paths.get(diretorioPai.toString(), novoNomeArquivo);
	}
	
	public Path getNovoPath(Path antigoPath,Path novoDiretorio) {
		return Paths.get(novoDiretorio.toString(), antigoPath.getFileName().toString());
	}
}

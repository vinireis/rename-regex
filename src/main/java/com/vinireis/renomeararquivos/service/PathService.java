package com.vinireis.renomeararquivos.service;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.stereotype.Service;

@Service
public class PathService {
	private static final String REGEX_PDF_ALURA = ".*_ Aula (\\d{1,2}) - Atividade (\\d{1,2}) (.*) _.*.pdf";

	public Path getNovoPath(Path antigoPath) {
		Path diretorioPai = antigoPath.getParent();
		Path nomeArquivo = antigoPath.getFileName();
		String novoNomeArquivo = getNovoNomeArquivo(nomeArquivo);
		return Paths.get(diretorioPai.toString(), novoNomeArquivo);
	}

	private String getNovoNomeArquivo(Path nomeArquivo) throws IllegalArgumentException {
		if (isPDFAlura(nomeArquivo)) {
			return renomeiaPDFAlura(nomeArquivo);
		}
		if (isVideoAlura(nomeArquivo)) {
			return renomeiaVideoAlura(nomeArquivo);
		}
		throw new IllegalArgumentException();
	}

	private boolean isPDFAlura(Path p) {
		return p.toString().matches(REGEX_PDF_ALURA);
	}

	private String renomeiaPDFAlura(Path p) {
		String numeroAlura = adicionaZero(p.toString().replaceAll(REGEX_PDF_ALURA, "$1"));
		String numeroAtividade = adicionaZero(p.toString().replaceAll(REGEX_PDF_ALURA, "$2"));
		String nomeAula = p.toString().replaceAll(REGEX_PDF_ALURA, "$3");
		String extensao = ".pdf";
		return buildNomeArquivo(numeroAlura, numeroAtividade, nomeAula, extensao);
	}

	private String adicionaZero(String numero) {
		return numero.length() == 1 ? "0" + numero : numero;
	}

	private String buildNomeArquivo(String numeroAlura, String numeroAtividade, String nomeAula, String extensao) {
		return numeroAlura + " " + numeroAtividade + " - " + nomeAula + extensao;
	}

	private boolean isVideoAlura(Path p) {
		return false;
	}

	private String renomeiaVideoAlura(Path p) {
		return "Lógica de programação II_ Aula 3 - Atividade 8 Desenhando com o mouse _ .pdf";
	}

}

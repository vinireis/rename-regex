package com.vinireis.renomeararquivos.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.stream.Stream;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class GerenciadorDeArquivosService {
	private static final String REGEX_PDF_ALURA = ".*_ Aula (\\d{1,2}) - Atividade (\\d{1,2}) (.*) _.*.pdf";

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

	public void renomeiaArquivosNo(@Valid @NotNull String diretorio) throws IOException {
		log.info("Os arquivos do diretório {} serão renomeados!", diretorio);
		Stream<Path> arquivosDoDiretorio = getArquivosDoDiretorio(diretorio);
		arquivosDoDiretorio.forEach(p -> renomeia(p));
	}

	private void renomeia(Path antigoPath) {
		try {
			Path novoPath = getNovoPath(antigoPath);
			moveArquivoParaNovoPath(antigoPath, novoPath);
		} catch (IllegalArgumentException e) {
			// log.info("Não foi encontrado padrão para renomear para o Path:
			// {}",antigoPath);
		}
	}

	private Path getNovoPath(Path antigoPath) {
		Path diretorioPai = antigoPath.getParent();
		Path nomeArquivo = antigoPath.getFileName();
		String novoNomeArquivo = getNovoNomeArquivo(nomeArquivo);
		Path novoPath = Paths.get(diretorioPai.toString(), novoNomeArquivo);
		return novoPath;
	}

	private void moveArquivoParaNovoPath(Path antigoPath, Path novoPath) {
		logMove(antigoPath, novoPath);
		try {
			Files.move(antigoPath, novoPath, StandardCopyOption.REPLACE_EXISTING);
		} catch (IOException e) {
			log.error("Não foi possivel renomear arquivo", e);
		}
	}

	private void logMove(Path antigoPath, Path novoPath) {
		log.info("O arquivo será movido de:");
		log.info("{}", antigoPath);
		log.info("Para:");
		log.info("{}", novoPath);
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

	private boolean isVideoAlura(Path p) {
		return false;
	}

	private String renomeiaVideoAlura(Path p) {
		return "Lógica de programação II_ Aula 3 - Atividade 8 Desenhando com o mouse _ .pdf";
	}

	private Stream<Path> getArquivosDoDiretorio(String diretorio) throws IOException {
		Path pathDiretorio = Paths.get(diretorio);
		if (isDirectory(pathDiretorio)) {
			log.info("É um diretorio");
			return Files.list(pathDiretorio);
		} else {
			throw new IOException("O diretorio passado não existe!");
		}
	}

	private boolean isDirectory(Path pathDiretorio) {
		return Files.isDirectory(pathDiretorio, LinkOption.NOFOLLOW_LINKS);
	}
}

package com.vinireis.renomeararquivos.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class DiretorioService {
	public Stream<Path> getArquivosDoDiretorio(String diretorio) throws IOException {
		Path pathDiretorio = Paths.get(diretorio);
		if (isDirectory(pathDiretorio)) {
			log.info("É um diretorio");
			return Files.list(pathDiretorio);
		} else {
			throw new IOException("O diretorio passado não existe!");
		}
	}

	public Stream<Path> getDiretoriosDoDiretorio(String diretorio) {
		try {
			Path pathDiretorio = Paths.get(diretorio);
			if (isDirectory(pathDiretorio)) {
				log.info("É um diretorio");
				return Files.list(pathDiretorio).filter(p -> isDirectory(p));
			} else {
				throw new IllegalArgumentException("O diretorio passado não existe!");
			}
		} catch (IOException e) {
			throw new IllegalArgumentException("O diretorio passado não existe!");
		}
	}

	private boolean isDirectory(Path pathDiretorio) {
		return Files.isDirectory(pathDiretorio, LinkOption.NOFOLLOW_LINKS);
	}

}

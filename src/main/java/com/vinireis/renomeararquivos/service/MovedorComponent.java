package com.vinireis.renomeararquivos.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class MovedorComponent {
	public void moveArquivoParaNovoPath(Path antigoPath, Path novoPath) {
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
}

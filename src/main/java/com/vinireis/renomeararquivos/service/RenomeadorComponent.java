package com.vinireis.renomeararquivos.service;

import java.nio.file.Path;

import org.springframework.stereotype.Component;

@Component
public class RenomeadorComponent {
	private PathService pathService;
	private MovedorComponent movedorComponent;

	public RenomeadorComponent(PathService pathService, MovedorComponent movedorComponent) {
		this.pathService = pathService;
		this.movedorComponent = movedorComponent;
	}

	public void renomeia(Path antigoPath) {
		try {
			Path novoPath = pathService.getNovoPath(antigoPath);
			movedorComponent.moveArquivoParaNovoPath(antigoPath, novoPath);
		} catch (IllegalArgumentException e) {
			// log.info("Não foi encontrado padrão para renomear para o Path:
			// {}",antigoPath);
		}
	}
}

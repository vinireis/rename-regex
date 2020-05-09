package com.vinireis.renomeararquivos.service;

import java.nio.file.Path;
import java.util.stream.Stream;

import org.springframework.stereotype.Component;

import com.vinireis.renomeararquivos.service.verificadordiretorios.VerificadoDeDiretoriosParaArquivo;

@Component
public class RenomeadorComponent {
	private PathService pathService;
	private MovedorComponent movedorComponent;
	private DiretorioService diretorioService;


	public RenomeadorComponent(PathService pathService, MovedorComponent movedorComponent,
			DiretorioService diretorioService) {
		this.pathService = pathService;
		this.movedorComponent = movedorComponent;
		this.diretorioService = diretorioService;
	}

	public void renomeia(Path antigoPath, String diretorio) {
		try {
			Path novoPath = pathService.getNovoPath(antigoPath);
			Stream<Path> diretoriosDoDiretorio = diretorioService.getDiretoriosDoDiretorio(diretorio);
			novoPath = getPathComNovoDiretorio(diretoriosDoDiretorio, novoPath);
			movedorComponent.moveArquivoParaNovoPath(antigoPath, novoPath);
		} catch (IllegalArgumentException e) {}
	}

	private Path getPathComNovoDiretorio(Stream<Path> diretoriosDoDiretorio, Path novoPath) {
		try {
			return diretoriosDoDiretorio
				.filter(d -> VerificadoDeDiretoriosParaArquivo.pertence(novoPath, d))
				.map(d -> pathService.getNovoPath(novoPath, d))
				.findAny().orElseThrow(() -> new IllegalArgumentException());
		} catch (IllegalArgumentException e) {
			return novoPath;
		}
	}
}

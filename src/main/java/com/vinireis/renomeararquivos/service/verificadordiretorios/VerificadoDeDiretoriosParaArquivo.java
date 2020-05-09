package com.vinireis.renomeararquivos.service.verificadordiretorios;

import java.nio.file.Path;

public  class VerificadoDeDiretoriosParaArquivo {
	private static final String REGEX_NUMERO_AULA = "(^\\d{1,2}).*";

	public static boolean pertence(Path arquivo, Path diretorio) {
		String numeroAulaArquivo = getNumeroAula(arquivo);
		String numeroAulaDiretorio = getNumeroAula(diretorio);
		return numeroAulaDiretorio.equals(numeroAulaArquivo);
	}

	private static String getNumeroAula(Path arquivo) {
		return arquivo.getFileName().toString().replaceAll(REGEX_NUMERO_AULA, "$1");
	}
}

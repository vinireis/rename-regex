package com.vinireis.renomeararquivos.service.builders;

import java.nio.file.Path;

public interface BuilderNomeArquivo {
	String getRegex();
	String build(Path antigoNomeArquivo);

	default boolean matches(Path p) {
		return p.toString().matches(getRegex());
	}
}

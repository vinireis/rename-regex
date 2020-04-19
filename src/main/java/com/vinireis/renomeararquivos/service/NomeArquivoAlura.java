package com.vinireis.renomeararquivos.service;

public class NomeArquivoAlura {
	private NomeArquivoAlura(Builder builder) {
		this.numeroAlura = adicionaZero(builder.numeroAlura);
		this.numeroAtividade = adicionaZero(builder.numeroAtividade);
		this.nomeAula = builder.nomeAula;
		this.extensao = builder.extensao;
	}

	private String numeroAlura;
	private String numeroAtividade;
	private String nomeAula;
	private String extensao;

	public String getNumeroAlura() {
		return numeroAlura;
	}

	public String getNumeroAtividade() {
		return numeroAtividade;
	}

	public String getNomeAula() {
		return nomeAula;
	}

	public String getExtensao() {
		return extensao;
	}
	
	public String toString() {
		return this.numeroAlura + " " + this.numeroAtividade + " - " + this.nomeAula + this.extensao;
	}

	private String adicionaZero(String numero) {
		return numero.length() == 1 ? "0" + numero : numero;
	}

	public static class Builder {
		private String numeroAlura;
		private String numeroAtividade;
		private String nomeAula;
		private String extensao;

		public Builder numeroAlura(String numeroAlura) {
			this.numeroAlura = numeroAlura;
			return this;
		}

		public Builder numeroAtividade(String numeroAtividade) {
			this.numeroAtividade = numeroAtividade;
			return this;
		}

		public Builder nomeAula(String nomeAula) {
			this.nomeAula = nomeAula;
			return this;
		}

		public Builder extensao(String extensao) {
			this.extensao = extensao;
			return this;
		}

		public NomeArquivoAlura build() {
			return new NomeArquivoAlura(this);
		}

	}
}

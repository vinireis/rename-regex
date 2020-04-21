package com.vinireis.renomeararquivos.service;

public class NomeArquivoAlura {
	private NomeArquivoAlura(Builder builder) {
		this.numeroAula = adicionaZero(builder.numeroAula);
		this.numeroAtividade = adicionaZero(builder.numeroAtividade);
		this.nomeAula = builder.nomeAula;
		this.extensao = builder.extensao;
	}

	private String numeroAula;
	private String numeroAtividade;
	private String nomeAula;
	private String extensao;

	public String getNumeroAlura() {
		return numeroAula;
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
		return this.numeroAula + " " + this.numeroAtividade + " - " + this.nomeAula + this.extensao;
	}

	private String adicionaZero(String numero) {
		return numero.length() == 1 ? "0" + numero : numero;
	}

	public static class Builder {
		private String numeroAula;
		private String numeroAtividade;
		private String nomeAula;
		private String extensao;

		public Builder numeroAula(String numeroAula) {
			this.numeroAula = numeroAula;
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

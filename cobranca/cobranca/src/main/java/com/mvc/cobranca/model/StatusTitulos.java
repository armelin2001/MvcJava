package com.mvc.cobranca.model;

public enum StatusTitulos {
	PENDENTE("Pendente"),
	PROXIMO("Proximo"),
	RECEBIDO("Recebido");
	private String descricao;
	StatusTitulos(String descricao){
		this.descricao = descricao;
	}
	public String getDescricao() {
		return descricao;
	}
}

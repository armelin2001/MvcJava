package models;

public enum StatusTitulo {
	PENDENTE("Pendente"),
	RECIBIDO("Recebido");
	private String descricao;
	StatusTitulo(String descricao){
		this.descricao = descricao;
	}
	public String getDescricao() {
		return descricao;
	}
}

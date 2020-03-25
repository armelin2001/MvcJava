package com.mvc.cobranca.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mvc.cobranca.model.StatusTitulos;
import com.mvc.cobranca.model.Titulo;
import com.mvc.cobranca.repositorio.Titulos;
import com.mvc.cobranca.repositorio.filter.TituloFilter;

@Service
public class CadastroTituloService {
	@Autowired
	private Titulos titulos;
	public void salvar(Titulo titulo) {
		titulos.save(titulo);
	}
	public void excluir(Long codigo) {
		titulos.deleteById(codigo);
	}
	public String receber(Long codigo) {
		Titulo titulo = titulos.getOne(codigo);
		titulo.setStatus(StatusTitulos.RECEBIDO);
		titulos.save(titulo);
		return StatusTitulos.RECEBIDO.getDescricao();
	}
	public List<Titulo> filtrar(TituloFilter filtro){
		String descricao = filtro.getDescricao() == null ? "%" : filtro.getDescricao();
		return titulos.findByDescricaoContaining(descricao);
	}
}

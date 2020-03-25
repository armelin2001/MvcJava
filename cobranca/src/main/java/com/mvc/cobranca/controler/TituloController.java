package com.mvc.cobranca.controler;


import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.mvc.cobranca.model.StatusTitulos;
import com.mvc.cobranca.model.Titulo;
import com.mvc.cobranca.repositorio.Titulos;



@Controller
@RequestMapping("/titulos")
public class TituloController {
	@Autowired
	private Titulos titulos;
	@RequestMapping("/novo")
	public ModelAndView novo(){
		ModelAndView mv = new ModelAndView("CadastroTitulo");
		return mv;
	}
	@RequestMapping(method= RequestMethod.POST)
	public ModelAndView salvar(Titulo titulo) {
		titulos.save(titulo);
		ModelAndView mv = new ModelAndView("CadastroTitulo");
		mv.addObject("mensagem","Titulo salvo com sucesso!");
		return mv;
	}
	@ModelAttribute("todosStatusTitulo")
	public List<StatusTitulos> todosStatusTitulo(){
		return Arrays.asList(StatusTitulos.values());
	}
	@RequestMapping
	public String pesquisar(){
		return "pesquisaTitulos";
	}
}








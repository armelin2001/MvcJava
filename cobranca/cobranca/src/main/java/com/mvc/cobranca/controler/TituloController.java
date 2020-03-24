package com.mvc.cobranca.controler;


import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.mvc.cobranca.model.StatusTitulos;
import com.mvc.cobranca.model.Titulo;
import com.mvc.cobranca.repositorio.Titulos;
import com.mvc.cobranca.service.CadastroTituloService;



@Controller
@RequestMapping("/titulos")
public class TituloController {
	private static final String CADASTRO_VIEW = "CadastroTitulo";
	@Autowired
	private Titulos titulos;
	@Autowired
	private CadastroTituloService cadastroTituloService;
	
	@RequestMapping("/novo")
	public ModelAndView novo(){
		ModelAndView mv = new ModelAndView(CADASTRO_VIEW);
		mv.addObject(new Titulo());
		return mv;
	}
	@RequestMapping(method= RequestMethod.POST)
	public String salvar(@Validated Titulo titulo, Errors errors, RedirectAttributes attributes) {
		if(errors.hasErrors()){
			return CADASTRO_VIEW;
		}
		cadastroTituloService.salvar(titulo);
		attributes.addFlashAttribute("mensagem","Titulo salvo com sucesso!");
		return "redirect:/titulos/novo";
	}
	@ModelAttribute("todosStatusTitulo")
	public List<StatusTitulos> todosStatusTitulo(){
		return Arrays.asList(StatusTitulos.values());
	}
	@RequestMapping
	public ModelAndView pesquisar(){
		List<Titulo> todosTitulos = titulos.findAll();
		ModelAndView mv= new ModelAndView("PesquisaTitulos"); 
		mv.addObject("titulos",todosTitulos);
		return mv;
	}
	@RequestMapping("{codigo}")
	public ModelAndView edicao(@PathVariable("codigo") Titulo titulo){
		ModelAndView mv = new ModelAndView(CADASTRO_VIEW);
		mv.addObject(titulo);
		return mv;
		
	}	
	@RequestMapping(value = "{codigo}", method=RequestMethod.POST)
	public String excluir(@PathVariable Long codigo, RedirectAttributes attributes) {
		cadastroTituloService.excluir(codigo);
		attributes.addFlashAttribute("mensagem","Titulo excluido com sucesso!");
		return "redirect:/titulos";
	}
	@RequestMapping(value="{codigo}/receber", method = RequestMethod.PUT)
	public @ResponseBody String receber(@PathVariable Long codigo){
		return cadastroTituloService.receber(codigo);
	}
			
}








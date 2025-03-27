package br.com.vemprafam.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.vemprafam.dao.DaoFuncionario;
import br.com.vemprafam.pojo.Funcionario;

@Controller
@RequestMapping("/funcionarios")
public class FuncionariosController {

	DaoFuncionario dao = new DaoFuncionario();
	@GetMapping
	String showFuncionariosHomeVazio(Model model) {
		return "funcionarios";
	}
	@GetMapping("/")
	String showFuncionariosHome(Model model) {
		return "funcionarios";
	}
	@GetMapping("/new")
	public String showFuncForm(Model model) {
		Funcionario funcionario = new Funcionario();
		model.addAttribute("funcionario", funcionario);
		return "create-func";
	}
	@PostMapping
	public String insert(@ModelAttribute Funcionario func) {
		dao.insert(func);
		return "redirect:funcionarios";
	}
}

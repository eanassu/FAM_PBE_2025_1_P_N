package br.com.vemprafam.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import br.com.vemprafam.dao.DaoAluno;
import br.com.vemprafam.pojo.Aluno;

@Controller
@RequestMapping("/alunos")
public class AlunosController {

    private DaoAluno dao = new DaoAluno();

    @GetMapping
    public String homeEmpty() {
        return "alunos"; // Retorna o nome do template da página inicial
    }

    @GetMapping("/")
    public String home() {
        return "alunos"; // Retorna o nome do template da página inicial
    }

    @GetMapping("/new")
    public String showInsertForm(Model model) {
        model.addAttribute("aluno", new Aluno());
        return "create-aluno";
    }

    @PostMapping
    public String createAluno(@ModelAttribute Aluno aluno) {
        dao.insert(aluno);
        return "redirect:alunos";
    }

    @GetMapping("/lista")
    public String showLista(Model model){
        List<Aluno> lista = dao.getLista();
        model.addAttribute("alunos", lista);
        return "lista-alunos";
    }

    @GetMapping("/busca")
    public String showBusca() {
        return "busca-aluno";
    }

    @GetMapping("/showUpdate")
    public String showEditAluno(@RequestParam int ra, Model model) {
        Aluno aluno = dao.buscarPeloRa(ra);
        model.addAttribute("aluno", aluno);
        return "alterar-aluno";
    }

    @PostMapping("/update")
    public String update(@ModelAttribute Aluno aluno) {
        dao.update(aluno);
        return "alunos";
    }

    @GetMapping("/apagar")
    public String showDelete(Model model) {
        Aluno aluno = new Aluno();
        model.addAttribute("aluno", aluno);
        return "apagar-aluno";
    }
    @PostMapping("/delete")
    public String delete(@ModelAttribute Aluno aluno) {
        dao.delete(aluno);
        return "alunos";
    }
}












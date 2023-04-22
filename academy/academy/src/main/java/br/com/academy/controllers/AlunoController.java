package br.com.academy.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.academy.dao.AlunoDao;
import br.com.academy.model.Aluno;

@Controller
public class AlunoController {

    @Autowired
    private AlunoDao alunoRepositorio;
    
    @GetMapping("/inserirAlunos")
    public ModelAndView InsertAlunos(Aluno aluno) {

        ModelAndView mv = new ModelAndView();
        mv.setViewName("aluno/formAluno");
        mv.addObject("aluno", new Aluno());
        return mv;

    }

    @PostMapping("/InsertAlunos")
    public ModelAndView inserirAluno(Aluno aluno) {

        ModelAndView mv = new ModelAndView();
        mv.setViewName("redirect:/alunosAdicionados");
        alunoRepositorio.save(aluno);
        return mv;

    }

    @GetMapping("/alunosAdicionados")
    public ModelAndView listagemAlunos() {

        ModelAndView mv = new ModelAndView();
        mv.setViewName("aluno/listAlunos");
        mv.addObject("alunosList", alunoRepositorio.findAll());
        return mv;

    }

    @GetMapping("/alterar/{id}")
    public ModelAndView alterar(@PathVariable("id") Integer id) {

        ModelAndView mv = new ModelAndView();
        mv.setViewName("aluno/alterar");
        Aluno aluno = alunoRepositorio.getOne(id);
        mv.addObject("aluno", aluno);
        return mv;

    }

    @PostMapping("/alterar")
    public ModelAndView alterar(Aluno aluno) {
        
        ModelAndView mv = new ModelAndView();
        alunoRepositorio.save(aluno);
        mv.setViewName("redirect:/alunosAdicionados");
        return mv;

    }

    @GetMapping("/excluir/{id}")
    public String excluirAluno(@PathVariable("id") Integer id) {

        alunoRepositorio.deleteById(id);
        return "redirect:/alunosAdicionados";

    }

}
package br.com.academy.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

    @PostMapping("InsertAlunos")
    public ModelAndView inserirAluno(@Valid Aluno aluno, BindingResult br) {

        ModelAndView mv = new ModelAndView();

        if(br.hasErrors()) {
            
            mv.setViewName("aluno/formAluno");
            mv.addObject("aluno");

        } else {

            mv.setViewName("redirect:/alunosAdicionados");
            alunoRepositorio.save(aluno);

        }

        return mv;

    }

    @GetMapping("alunosCadastrados")
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

    @GetMapping("/filtroAlunos")
    public ModelAndView filtroAlunos() {

        ModelAndView mv = new ModelAndView();
        mv.setViewName("aluno/filtroAlunos");
        return mv;

    }

    @GetMapping("alunosAtivos")
    public ModelAndView listaAlunosAtivos() {

        ModelAndView mv = new ModelAndView();
        mv.setViewName("aluno/alunosAtivos");
        mv.addObject("alunosAtivos", alunoRepositorio.findByStatusAtivos());
        return mv;

    }

    @GetMapping("alunosInativos")
    public ModelAndView listaAlunosInativos() {

        ModelAndView mv = new ModelAndView();
        mv.setViewName("aluno/alunosInativos");
        mv.addObject("alunosInativos", alunoRepositorio.findByStatusInativos());
        return mv;

    }

    @GetMapping("alunosCancelados")
    public ModelAndView listaAlunosCancelados() {

        ModelAndView mv = new ModelAndView();
        mv.setViewName("aluno/alunosCancelados");
        mv.addObject("alunosCancelados", alunoRepositorio.findByStatusCancelados());
        return mv;

    }

    @GetMapping("alunosTrancados")
    public ModelAndView listaAlunosTrancados() {

        ModelAndView mv = new ModelAndView();
        mv.setViewName("aluno/alunosTrancados");
        mv.addObject("alunosTrancados", alunoRepositorio.findByStatusTrancados());
        return mv;

    }

    @PostMapping("pesquisarAluno")
    public ModelAndView pesquisarAluno(@RequestParam(required = false) String nome) {

        ModelAndView mv = new ModelAndView();
        List<Aluno> listAlunos;

        if(nome == null || nome.trim().isEmpty()) {

            listAlunos = alunoRepositorio.findAll();

        } else {

            listAlunos = alunoRepositorio.findByNomeContainingIgnoreCase(nome);

        }

        mv.addObject("listaDeAlunos", listAlunos);
        mv.setViewName("aluno/pesquisaResultado");
        return mv;

    }

}
package br.com.academy.controllers;

import java.security.NoSuchAlgorithmException;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.academy.dao.UsuarioDao;
import br.com.academy.model.Aluno;
import br.com.academy.model.Usuario;
import br.com.academy.service.ServiceExc;
import br.com.academy.service.UsuarioService;
import br.com.academy.util.Util;
import jakarta.servlet.http.HttpSession;

@Controller
public class UsuarioController {

    @Autowired
    private UsuarioDao usuarioRepositorio;

    @Autowired
    private UsuarioService usuarioService;
    
    @GetMapping("/")
    public ModelAndView login() {
        
        ModelAndView mv = new ModelAndView();
        mv.addObject("usuario", new Usuario());
        mv.setViewName("login/login");
        return mv;

    }

    @GetMapping("/index")
    public ModelAndView index() {
        ModelAndView mv = new ModelAndView(); // inicializando e instânciando o objeto ModelAndView
        mv.setViewName("home/index");
        mv.addObject("aluno", new Aluno());
        return mv;
    }

    @GetMapping("/cadastro")
    public ModelAndView cadastrar() {
        
        ModelAndView mv = new ModelAndView();
        mv.addObject("usuario", new Usuario());
        mv.setViewName("login/cadastro");
        return mv;

    }

    @PostMapping("salvarUsuario")
    public ModelAndView cadastrar(Usuario usuario) throws Exception {

        ModelAndView mv = new ModelAndView();
        usuarioService.salvarUsuario(usuario);
        mv.setViewName("redirect:/");
        return mv;

    }

    @PostMapping("/login")
    public ModelAndView login(@Valid Usuario usuario, BindingResult br, HttpSession session) throws NoSuchAlgorithmException, ServiceExc {

        ModelAndView mv = new ModelAndView();
        mv.addObject("usuario", new Usuario());
        
        if(br.hasErrors()) {

            mv.setViewName("login/login");

        }

        Usuario usuarioLogin = usuarioService.loginUsuario(usuario.getUser(), Util.md5(usuario.getSenha()));

        if(usuarioLogin == null) {

            mv.setViewName("login/login");
            mv.addObject("msg", "Usuário não encontrado. Tente novamente");

        } else {

            session.setAttribute("usuarioLogado", usuarioLogin);

            return index();

        }

        return mv;

    }

    @PostMapping("/logout")
    public ModelAndView logout(HttpSession session) {

        session.invalidate();
        return login();

    }

}
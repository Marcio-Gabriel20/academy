package br.com.academy.service;

import java.security.NoSuchAlgorithmException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.academy.dao.UsuarioDao;
import br.com.academy.exceptions.CriptoExistException;
import br.com.academy.exceptions.EmailExistsException;
import br.com.academy.model.Usuario;
import br.com.academy.util.Util;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioDao usuarioRepositorio;
    
    public void salvarUsuario(Usuario usuario) throws Exception{

        try {
            
            if(usuarioRepositorio.findByEmail(usuario.getEmail()) != null) {

                throw new EmailExistsException("Já existe um usuário cadastrado com o e-mail: " + usuario.getEmail());

            }

            usuario.setSenha(Util.md5(usuario.getSenha()));

        } catch (NoSuchAlgorithmException e) {
            
            throw new CriptoExistException("Erro na criptografia da senha");

        }

        usuarioRepositorio.save(usuario);

    }

    public Usuario loginUsuario(String usuario, String senha) throws ServiceExc {

        Usuario usuarioLogin = usuarioRepositorio.buscarLogin(usuario, senha);
        return usuarioLogin;

    }

}
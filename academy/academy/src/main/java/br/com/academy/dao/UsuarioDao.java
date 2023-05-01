package br.com.academy.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.academy.model.Usuario;

public interface UsuarioDao extends JpaRepository<Usuario, Long> {
    
    @Query("select e from Usuario e where e.email = :email")
    public Usuario findByEmail(String email);

    @Query("select u from Usuario u where u.user = :usuario and u.senha = :senha")
    public Usuario buscarLogin(String usuario, String senha);

}
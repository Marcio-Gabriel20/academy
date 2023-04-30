package br.com.academy.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.academy.model.Aluno;

public interface AlunoDao extends JpaRepository<Aluno, Integer> {

    // JPQL

    @Query("select a from Aluno a where a.status = 'ATIVO' ")
    public List<Aluno> findByStatusAtivos();

    @Query("select i from Aluno i where i.status = 'INATIVO' ")
    public List<Aluno> findByStatusInativos();

    @Query("select c from Aluno c where c.status = 'CANCELADO' ")
    public List<Aluno> findByStatusCancelados();

    @Query("select t from Aluno t where t.status = 'TRANCADO' ")
    public List<Aluno> findByStatusTrancados();

    // Pesquisar Alunos

    public List<Aluno> findByNomeContainingIgnoreCase(String nome);

}
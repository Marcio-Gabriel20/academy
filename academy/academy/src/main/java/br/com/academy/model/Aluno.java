package br.com.academy.model;

import br.com.academy.enums.Curso;
import br.com.academy.enums.Status;
import br.com.academy.enums.Turno;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Aluno {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "nome")
    private String nome;

    @Column(name = "curso")
    @Enumerated(EnumType.STRING)
    private Curso curso;
    // private String curso;

    @Column(name = "matricula")
    private String matricula;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private Status status;
    // private String status;

    @Column(name = "turno")
    @Enumerated(EnumType.STRING)
    private Turno turno;
    // private String turno;

}
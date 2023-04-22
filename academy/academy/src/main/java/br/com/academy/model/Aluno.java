package br.com.academy.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

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
    @Size(min = 5, max = 35, message = "O nome deve conter no mínimo 5 caracteres.")
    @NotBlank(message = "O nome não pode ser vazio.")
    private String nome;

    @Column(name = "curso")
    @Enumerated(EnumType.STRING)
    @NotNull(message = "O campo 'curso' não pode ser nulo.")
    private Curso curso;
    // private String curso;

    @Column(name = "matricula")
    @NotNull(message = "Clique no botão 'Gerar' para que a matrícula seja gerada automáticamente.")
    private String matricula;

    @Column(name = "status")
    @NotNull(message = "O campo 'status' não pode ser nulo.")
    @Enumerated(EnumType.STRING)
    private Status status;
    // private String status;

    @Column(name = "turno")
    @Enumerated(EnumType.STRING)
    @NotBlank(message = "O campo 'turno' não pode ser vazio.")
    @Size(min = 4, message = "No mínimo de 4 caracteres.")
    private Turno turno;
    // private String turno;

}
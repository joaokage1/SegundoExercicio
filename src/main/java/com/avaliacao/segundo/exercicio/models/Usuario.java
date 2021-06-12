package com.avaliacao.segundo.exercicio.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "usuario")
public class Usuario implements Serializable {

    @Id
    private Long id;
    @NotBlank(message = "Nome é requerido")
    private String nome;
    @NotBlank(message = "Senha é requerida")
    private String senha;
    @Email
    @NotEmpty(message = "Email é requerido")
    private String email;
}

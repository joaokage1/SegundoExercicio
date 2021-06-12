package com.avaliacao.segundo.exercicio.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "marca")
public class Marca implements Serializable {

    @Id
    @SequenceGenerator(name="gerador", sequenceName="marca_marca_id_seq", allocationSize=1)
    @GeneratedValue(generator="gerador", strategy= GenerationType.SEQUENCE)
    @Column(name = "marca_id")
    private Long marcaId;

    @NotBlank(message = "Nome é requerido")
    private String nome;

}

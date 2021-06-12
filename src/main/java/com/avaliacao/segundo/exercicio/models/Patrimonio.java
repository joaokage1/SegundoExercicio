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
@Table(name = "patrimonio")
public class Patrimonio implements Serializable {

    @Id
    @SequenceGenerator(name="gerador", sequenceName="patrimonio_ntombo_seq", allocationSize=1)
    @GeneratedValue(generator="gerador", strategy= GenerationType.SEQUENCE)
    @Column(name = "n_tombo")
    private Long nTombo;

    @NotBlank(message = "Nome é requerido")
    private String nome;

    @NotBlank(message = "Id da marca é requerido")
    @Column(name = "marca_id")
    private Long marcaId;

    private String descricao;
}

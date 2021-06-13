package com.avaliacao.segundo.exercicio.models.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioResponse extends BaseResponse{

    private String nome;
    private String token;
}

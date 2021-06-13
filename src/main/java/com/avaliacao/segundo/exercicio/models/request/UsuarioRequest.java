package com.avaliacao.segundo.exercicio.models.request;

import com.avaliacao.segundo.exercicio.models.Usuario;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioRequest {

    private Usuario usuario;
}

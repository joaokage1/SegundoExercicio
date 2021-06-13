package com.avaliacao.segundo.exercicio.services;

import com.avaliacao.segundo.exercicio.models.request.UsuarioRequest;
import com.avaliacao.segundo.exercicio.models.response.UsuarioResponse;

public interface IUsuarioService {

    public UsuarioResponse cadastrarUsuario(UsuarioRequest request);
    public UsuarioResponse entrar(UsuarioRequest request);
}

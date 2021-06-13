package com.avaliacao.segundo.exercicio.services.impl;

import com.avaliacao.segundo.exercicio.models.request.PatrimonioRequest;
import com.avaliacao.segundo.exercicio.models.response.PatrimonioResponse;
import com.avaliacao.segundo.exercicio.repositories.IPatrimonioRepository;
import com.avaliacao.segundo.exercicio.services.IPatrimonioService;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.io.Serializable;

@Component
@Data
@AllArgsConstructor
public class PatrimonioService implements IPatrimonioService, Serializable {

    private final IPatrimonioRepository repository;


    @Override
    public PatrimonioResponse insertPatrimonio(PatrimonioRequest request) {
        return null;
    }

    @Override
    public PatrimonioResponse buscarTodos() {
        return null;
    }

    @Override
    public PatrimonioResponse buscarPorNoTombo(PatrimonioRequest request) {
        return null;
    }

    @Override
    public PatrimonioResponse buscarPorNome(PatrimonioRequest request) {
        return null;
    }

    @Override
    public PatrimonioResponse buscarPorMarcaId(PatrimonioRequest request) {
        return null;
    }

    @Override
    public PatrimonioResponse apagarPatrimonioPeloNoTombo(PatrimonioRequest request) {
        return null;
    }

    @Override
    public PatrimonioResponse editarPatrimonio(PatrimonioRequest request) {
        return null;
    }
}

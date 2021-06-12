package com.avaliacao.segundo.exercicio.services.impl;

import com.avaliacao.segundo.exercicio.models.request.PatrimonioRequest;
import com.avaliacao.segundo.exercicio.repositories.IMarcaRepository;
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
    private final IMarcaRepository marcaRepository;

    @Override
    public void insertPatrimonio(PatrimonioRequest request) {
        getRepository().save(request.getPatrimonio());
    }
}
